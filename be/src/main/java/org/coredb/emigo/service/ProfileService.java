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
import org.coredb.emigo.model.EmigoLogin;

import javax.ws.rs.NotAcceptableException;
import java.nio.file.AccessDeniedException;
import org.springframework.web.client.RestClientException;
import org.coredb.emigo.api.NotFoundException;
import java.security.InvalidParameterException;
import java.io.IOException;

import org.coredb.emigo.model.Contact;
import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.jpa.repository.AccountRepository;
import org.coredb.emigo.model.GpsLocation;
import org.coredb.emigo.model.Profile;
import org.coredb.emigo.service.util.PasswordUtil;

import static org.coredb.emigo.NameRegistry.*;
import org.coredb.emigo.service.ConfigService;
import org.coredb.emigo.TokenUtil;

@Service
public class ProfileService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ConfigService configService;

  public Integer getRevision(Account account) {
    return account.getProfileRevision();
  }

  public Profile getProfile(Account account) {

    // convert to profile
    Profile profile = new Profile();
    profile.setRevision(account.getProfileRevision());
    profile.setSearchable(account.getSearchable());
    profile.setAvailable(account.getAvailable());
    profile.setGps(account.getGps());
    profile.setGpsTimestamp(account.getGpsTimestamp());
    profile.setGpsLongitude(account.getGpsLongitude());
    profile.setGpsLatitude(account.getGpsLatitude());
    return profile;
  }

  private Profile setProfile(Account act) {

    // update profile revision
    act.setProfileRevision(act.getProfileRevision() + 1);
    
    // save update fields
    return getProfile(accountRepository.save(act));
  }

  @Transactional
  public Profile setSearchable(Account account, Boolean flag) throws Exception {

    // update searchable flag
    account.setSearchable(flag);
    return setProfile(account);
  }

  @Transactional
  public Profile setAvailable(Account account, Boolean flag) throws Exception {

    // update searchable flag
    account.setAvailable(flag);
    return setProfile(account);
  }

  @Transactional
  public Profile setLocation(Account account, GpsLocation gps, Long expires) throws Exception {

    // update location
    if(gps != null) {
      account.setGps(true);
      account.setGpsLongitude(gps.getLongitude());
      account.setGpsLatitude(gps.getLatitude());
      account.setGpsAltitude(gps.getAltitude());
    }
    else {
      account.setGps(false);
    }

    // update timeout
    if(expires != null) {
      account.setGpsTimestamp(expires);
    }
    else {
      Long cur = Instant.now().getEpochSecond();
      cur += configService.getServerNumValue(SC_LOCATION_EXPIRE, (long)300);
      account.setGpsTimestamp(cur);      
    }
    
    // save and return
    return setProfile(account);
  }
}

