package org.coredb.emigo.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotAcceptableException;
import org.coredb.emigo.api.NotFoundException;
import java.nio.file.AccessDeniedException;
import java.security.InvalidParameterException;
import static org.coredb.emigo.NameRegistry.*;
import org.coredb.emigo.service.ConfigService.ConfigServiceValue;

import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.jpa.repository.AccountRepository;

@Service
public class AuthService {

  @Autowired
  private ConfigService configService;

  @Autowired
  private AccountRepository accountRepository;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public void statToken(String token) throws AccessDeniedException {
    compareToken(SC_STAT_TOKEN, token);
  }

  private void compareToken(String id, String token) throws AccessDeniedException {

    // compare with stored token
    ConfigServiceValue config = configService.getServerConfig(id);
    if(config == null || config.strValue == null) {
      throw new AccessDeniedException("server token not set");
    }
    if(!config.strValue.equals(token)) {
      throw new AccessDeniedException("incorrect server token");
    }
  }

  public Account loginToken(String token) throws InvalidParameterException {
  
    // compare with stored tokens
    Account act = accountRepository.findOneByLoginToken(token);
    if(act == null) {
      throw new InvalidParameterException("login token not found");
    }
    if(!act.getEnabled()) {
      throw new NotAcceptableException("account is disabled");
    }
    return act;
  }
}

