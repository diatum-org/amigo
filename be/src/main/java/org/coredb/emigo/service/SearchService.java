package org.coredb.emigo.service;

import java.util.*;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;

import javax.ws.rs.NotAcceptableException;
import java.nio.file.AccessDeniedException;
import org.springframework.web.client.RestClientException;
import org.coredb.emigo.api.NotFoundException;
import java.security.InvalidParameterException;
import java.io.IOException;

import org.coredb.emigo.service.util.PageUtil;
import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.jpa.repository.AccountRepository;
import org.coredb.emigo.model.GpsLocation;
import org.coredb.emigo.model.SearchArea;
import org.coredb.emigo.model.Amigo;
import org.coredb.emigo.model.Contact;

import org.coredb.emigo.service.specification.AccountSpecificationDescription;
import org.coredb.emigo.service.specification.AccountSpecificationEmigo;
import org.coredb.emigo.service.specification.AccountSpecificationExpires;
import org.coredb.emigo.service.specification.AccountSpecificationGps;
import org.coredb.emigo.service.specification.AccountSpecificationLocation;
import org.coredb.emigo.service.specification.AccountSpecificationMinLongitude;
import org.coredb.emigo.service.specification.AccountSpecificationMaxLongitude;
import org.coredb.emigo.service.specification.AccountSpecificationMinLatitude;
import org.coredb.emigo.service.specification.AccountSpecificationMaxLatitude;
import org.coredb.emigo.service.specification.AccountSpecificationMinAltitude;
import org.coredb.emigo.service.specification.AccountSpecificationMaxAltitude;
import org.coredb.emigo.service.specification.AccountSpecificationName;
import org.coredb.emigo.service.specification.AccountSpecificationHandle;
import org.coredb.emigo.service.specification.AccountSpecificationSearchable;

@Service
public class SearchService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AccountRepository accountRepository;

  public List<Contact> search(Account account, String match, String name, String description, String location, SearchArea area, Integer offset, Integer limit) {
    // where searchable
System.out.println("SEARCHABLE");
    Specifications<Account> spec = Specifications.where(new AccountSpecificationSearchable(true));    
System.out.println("DONE");

    // where not self
    spec = spec.and(new AccountSpecificationEmigo(account.getEmigoId()));

    // where one field matches
    if(match != null && match.equals("") != true) {
      String[] tokens = match.split(" ");
      if(tokens.length > 4) {
        Specifications<Account> orList = Specifications.where(new AccountSpecificationName(match));
        orList = orList.or(new AccountSpecificationDescription(match));
        orList = orList.or(new AccountSpecificationHandle(match));
        orList = orList.or(new AccountSpecificationLocation(match));
        spec = spec.and(orList);
      }
      else {
        for(String token: tokens) {
          Specifications<Account> orList = Specifications.where(new AccountSpecificationName(token));
          orList = orList.or(new AccountSpecificationDescription(token));
          orList = orList.or(new AccountSpecificationHandle(token));
          orList = orList.or(new AccountSpecificationLocation(token));
          spec = spec.and(orList);
        }
      }
    }

    // where name matches
    if(name != null) {
      spec = spec.and(new AccountSpecificationName(name));
    }

    // where description matches
    if(description != null) {
      spec = spec.and(new AccountSpecificationDescription(description));
    }

    // where location matches
    if(location != null) {
      spec = spec.and(new AccountSpecificationLocation(location));
    }

    // where search area matches
    if(area != null) {
      Long cur = Instant.now().getEpochSecond();
      Specifications<Account> andList = Specifications.where(new AccountSpecificationGps(true));
      andList = andList.and(new AccountSpecificationExpires(cur));
      andList = andList.and(new AccountSpecificationMinLongitude(area.getMin().getLongitude()));
      andList = andList.and(new AccountSpecificationMaxLongitude(area.getMax().getLongitude()));
      andList = andList.and(new AccountSpecificationMinLatitude(area.getMin().getLatitude()));
      andList = andList.and(new AccountSpecificationMaxLatitude(area.getMax().getLatitude()));
      andList = andList.and(new AccountSpecificationMinAltitude(area.getMin().getAltitude()));
      andList = andList.and(new AccountSpecificationMaxAltitude(area.getMax().getAltitude()));
      spec = spec.and(andList);
    }

    // get result view
    Pageable view = PageUtil.getPage(offset, limit, "id", false);

    // retrieve data
    Page<Account> page = accountRepository.findAll(spec, view);

    // retrun list as base class
    @SuppressWarnings("unchecked")
    List<Contact> emigos = (List<Contact>)(List<?>)page.getContent();
    return emigos;
  }
}
