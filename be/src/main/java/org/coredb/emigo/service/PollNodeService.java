package org.coredb.emigo.service;

import java.util.*;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.coredb.emigo.NameRegistry.*;
import org.coredb.emigo.model.AccountStatus;

@Service
public class PollNodeService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private Long available = (long)0;

  @Autowired
  private ConfigService configService;

  public long poll() {

    String cluster = configService.getServerStringValue(SC_CLUSTER_URL, null);
    String token = configService.getServerStringValue(SC_CLUSTER_STATUS, null);
    if(cluster != null && token != null) {
      String url = cluster + "/admin/status?token=" + token;
      RestTemplate restTemplate = new RestTemplate();
      try {
        AccountStatus status = restTemplate.getForObject(url, AccountStatus.class);
        available = status.getTotal() - status.getCurrent();
      }
      catch(RestClientException e) {
        log.error("failed to retrive available cluster accounts");
      }
    }
    else {
      log.warn("cluster not connfigured");
    }
          
    // return configured poll interval
    return configService.getServerNumValue(SC_POLL_NODE_INTERVAL, (long)15).longValue();
  }

  public Long getAvailable() {
    return this.available;
  }
  
}

