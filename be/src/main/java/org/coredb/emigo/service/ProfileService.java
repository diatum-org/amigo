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
import org.coredb.emigo.jpa.entity.Confirm;
import org.coredb.emigo.jpa.repository.ConfirmRepository;
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
  private ConfirmRepository confirmRepository;

  @Autowired
  private ConfigService configService;

  public Integer getRevision(Account account) {
    return account.getProfileRevision();
  }

  public Profile getProfile(Account account) {

    // convert to profile
    Profile profile = new Profile();
    profile.setRevision(account.getProfileRevision());
    profile.setPhoneNumber(account.getPhoneNumber());
    profile.setConfirmedPhone(account.getConfirmedPhone());
    profile.setEmailAddress(account.getEmailAddress());
    profile.setConfirmedEmail(account.getConfirmedEmail());
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
  public void setPassword(Account account, String password, String update) throws IllegalArgumentException, Exception {
  
    // compare password
    String salt = account.getSalt();
    String pass = PasswordUtil.prepare(password, salt);
    if(!pass.equals(account.getPassword())) {
      throw new IllegalArgumentException("incorrect password");
    }

    // create new salt and password
    salt = PasswordUtil.salt();
    pass = PasswordUtil.prepare(update, salt);
    
    // save new password
    account.setSalt(salt);
    account.setPassword(pass);
    accountRepository.save(account);
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
  public Profile setPhone(Account account, String phoneNumber) throws Exception {

    // update phone number
    account.setPhoneNumber(phoneNumber);
    account.setEmailAddress(null);
    account.setConfirmedPhone(false);
    account.setConfirmedEmail(false);
    account.setProfileRevision(account.getProfileRevision() + 1);
    Account act = accountRepository.save(account); 
    confirmContact(act);
    return getProfile(act);
  }

  @Transactional
  public Profile setEmail(Account account, String emailAddress) throws Exception {
  
    // update email address
    account.setEmailAddress(emailAddress);
    account.setPhoneNumber(null);
    account.setConfirmedPhone(false);
    account.setConfirmedEmail(false);
    account.setProfileRevision(account.getProfileRevision() + 1);
    Account act = accountRepository.save(account); 
    confirmContact(act);
    return getProfile(act);
  }

  @Transactional
  public void confirmContact(Account act) {
    
    // add new confirmation entry
    String[] cmd;
    Long cur = Instant.now().getEpochSecond();
    String token = TokenUtil.getToken();
    Confirm confirm = new Confirm();
    confirm.setAccount(act);
    String email = act.getEmailAddress();
    String phone = act.getPhoneNumber();
    if(email != null) {
      confirm.setEmail(email);
      String[] msg = { "bash", "/opt/emigo/confirm_email.sh", email, token };
      cmd = msg;
    }
    else {
      confirm.setPhone(phone);
      String[] msg = { "bash", "/opt/emigo/confirm_phone.sh", phone, token };
      cmd = msg;
    }
    confirm.setIssued(cur);
    confirm.setExpires(cur + configService.getServerNumValue(SC_CONFIRM_EXPIRE, (long)3600));
    confirm.setToken(token);
    confirmRepository.save(confirm);
    setProcess(cmd);
  }

  private void setProcess(String[] cmd) {

    // send confirmation link
    try {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command(cmd);
      Process process = processBuilder.start();
    }
    catch(Exception e) {
      log.error("confirmation process failed");
    }
  }

  @Transactional
  public void resetPassword(String email, String phone) throws NotFoundException {
    String token = TokenUtil.getToken();
    Long cur = Instant.now().getEpochSecond();
    Long exp = cur + configService.getServerNumValue(SC_RESET_EXPIRE, (long)3600);
    
    if(email != null) {
      Account act = accountRepository.findOneByEmailAddressAndConfirmedEmail(email, true);
      if(act == null) {
        throw new NotFoundException(404, "email address not foudn");
      }
      act.setResetToken(token);
      act.setResetIssued(cur);
      act.setResetExpires(exp);
      accountRepository.save(act);
      String[] cmd = { "bash", "/opt/emigo/reset_email.sh", email, token };
      setProcess(cmd);
    }

    if(phone != null) {
      Account act = accountRepository.findOneByPhoneNumberAndConfirmedPhone(phone, true);
      if(act == null) {
        throw new NotFoundException(404, "phone number not found");
      }
      act.setResetToken(token);
      act.setResetIssued(cur);
      act.setResetExpires(exp);
      accountRepository.save(act);
      String[] cmd = { "bash", "/opt/emigo/reset_phone.sh", phone, token };
      setProcess(cmd);
    }  
  }

  public Contact getReset(String token) throws NotFoundException, AccessDeniedException {
    return getResetAccount(token);
  }

  private Account getResetAccount(String token) throws NotFoundException, AccessDeniedException {

    // retrieve account of reset token
    Account act = accountRepository.findOneByResetToken(token);
    if(act == null) {
      throw new NotFoundException(404, "token not found");
    }
    
    Long cur = Instant.now().getEpochSecond();
    if(cur < act.getResetIssued() || cur > act.getResetExpires()) {
      throw new AccessDeniedException("token has expired");
    }
    return act;
  }

  @Transactional
  public void setReset(String token, String password) throws NotFoundException, AccessDeniedException, Exception {

    Account act = getResetAccount(token);

    // retreive accounnt of reset 
    String salt = PasswordUtil.salt();
    String pass = PasswordUtil.prepare(password, salt);

    act.setResetExpires(act.getResetIssued());  // invalidate token
    act.setSalt(salt);
    act.setPassword(pass);
    accountRepository.save(act);
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

