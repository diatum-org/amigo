package org.coredb.emigo.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;

import org.coredb.emigo.jpa.entity.ServerConfig;
import org.coredb.emigo.jpa.entity.ServerStat;
import org.coredb.emigo.jpa.repository.ServerStatRepository;
import org.coredb.emigo.jpa.repository.AccountRepository;
import org.coredb.emigo.NameRegistry;

import org.coredb.emigo.model.SystemStat;

import org.coredb.emigo.service.util.PageUtil;

@Service
public class ServerStatService {

  @Autowired
  private ServerStatRepository serverStatRepository;

  @Autowired
  private ConfigService configService;

  @Autowired
  private AccountRepository accountRepository;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private Long requests = (long)0;

  public Long incrementRequest() {
    requests++;
    return requests;
  }

  public List<SystemStat> getStats(Integer offset, Integer limit) {

    // get result view
    Pageable view = PageUtil.getPage(offset, limit, "timestamp", false);

    // retrieve data
    Page<ServerStat> page = serverStatRepository.findAll(view);

    @SuppressWarnings("unchecked")
    List<SystemStat> sys = (List<SystemStat>)(List<?>)page.getContent();
    return sys;
  }

  @Transactional
  public void addStat(Integer processor, Long memory, Long storage) {
    ServerStat system = new ServerStat();
    Date date = new Date();
    system.setTimestamp((int)(date.getTime()/1000));
    system.setProcessor(processor);
    system.setMemory(memory);
    system.setStorage(storage);
    system.setRequests(requests);
    system.setAccounts(accountRepository.count());
    serverStatRepository.save(system);

    // reset requests
    requests = (long)0;

    // truncate list
    Long max = configService.getServerNumValue(NameRegistry.SC_STAT_COUNT, (long)1024);
    Pageable view = PageUtil.getPage(max.intValue(), 1, "timestamp", false);
    Page<ServerStat> page = serverStatRepository.findAll(view);
    List<ServerStat> stats = page.getContent();
    if(!stats.isEmpty()) {
      serverStatRepository.deleteByTimestampLessThanEqual(stats.get(0).getTimestamp());
    }
  }
}

