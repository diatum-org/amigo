package org.coredb.emigo.service;

import java.util.*;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.coredb.emigo.model.Contact;

import java.nio.file.AccessDeniedException;
import org.coredb.emigo.api.NotFoundException;

import org.coredb.emigo.jpa.entity.Confirm;
import org.coredb.emigo.jpa.repository.ConfirmRepository;

import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.jpa.repository.AccountRepository;

@Service
public class ConfirmService {

  @Autowired
  ConfirmRepository confirmRepository;

  @Autowired
  AccountRepository accountRepository;

  private Confirm getEntity(String token) throws AccessDeniedException, NotFoundException {
    
    Confirm confirm = confirmRepository.findOneByToken(token);
    if(confirm == null) {
      throw new NotFoundException(404, "token not found");
    }

    Long cur = Instant.now().getEpochSecond();
    if(cur > confirm.getExpires() || cur < confirm.getIssued()) {
      throw new AccessDeniedException("token has expired");
    }

    return confirm;
  }

  public Contact getConfirmation(String token) throws AccessDeniedException, NotFoundException {
    Confirm confirm = getEntity(token);
    return confirm.getAccount();
  }

  @Transactional
  public void setConfirmation(String token) throws AccessDeniedException, NotFoundException {
    Confirm confirm = getEntity(token);
    Account act = confirm.getAccount();
    act.setEmailAddress(confirm.getEmail());
    act.setConfirmedEmail(true);
    act.setPhoneNumber(confirm.getPhone());
    act.setConfirmedPhone(true);
    act.setProfileRevision(act.getProfileRevision() + 1);
    accountRepository.save(act);
  }
}
