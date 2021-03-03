package org.coredb.emigo.service;

import java.util.*;
import java.security.*;
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

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.ws.rs.NotAcceptableException;
import java.nio.file.AccessDeniedException;
import org.springframework.web.client.RestClientException;
import org.coredb.emigo.api.NotFoundException;
import java.security.InvalidParameterException;
import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import org.coredb.emigo.model.Emigo;
import org.coredb.emigo.model.Contact;
import org.coredb.emigo.model.NodeConnection;
import org.coredb.emigo.model.UserEntry;
import org.coredb.emigo.model.ServiceAccess;
import org.coredb.emigo.model.LinkMessage;
import org.coredb.emigo.model.EmigoToken;
import org.coredb.emigo.model.EmigoMessage;
import org.coredb.emigo.service.util.PasswordUtil;
import org.coredb.emigo.service.util.EmigoUtil;
import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.jpa.repository.AccountRepository;

import static org.coredb.emigo.NameRegistry.*;
import org.coredb.emigo.model.AccountStatus;
import org.coredb.emigo.model.Result;
import org.coredb.emigo.TokenUtil;

@Service
public class AccountService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ConfigService configService;

  private ServiceAccess getAccess() {
    ServiceAccess access = new ServiceAccess();
    access.setEnableShow(true);
    access.setEnableIdentity(true);
    access.setEnableProfile(true);
    access.setEnableGroup(true);
    access.setEnableShare(true);
    access.setEnablePrompt(true);
    access.setEnableService(true);
    access.setEnableIndex(true);
    access.setEnableUser(true);
    access.setEnableAccess(true);
    access.setEnableAccount(true);
    return access;
  }

  private LinkMessage getCreateLink() throws RestClientException {

    // construct request url
    String token = configService.getServerStringValue(SC_APP_TOKEN, null);
    String base = configService.getServerStringValue(SC_APP_NODE, null);
    String url = base + "/access/services/created?token=" + token;

    // construct rest post
    RestTemplate rest = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<ServiceAccess> request = new HttpEntity<ServiceAccess>(getAccess(), headers);
    return rest.postForObject(url, request, LinkMessage.class);
  }

  private LinkMessage getAttachLink(String emigoId) throws RestClientException {

    // construct request url
    String token = configService.getServerStringValue(SC_APP_TOKEN, null);
    String base = configService.getServerStringValue(SC_APP_NODE, null);
    String url = base + "/access/services/attached?token=" + token + "&emigoId=" + emigoId;

    // construct rest post
    RestTemplate rest = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<ServiceAccess> request = new HttpEntity<ServiceAccess>(getAccess(), headers);
    return rest.postForObject(url, request, LinkMessage.class);
  }

  private EmigoToken createAccount(String cluster, String token, LinkMessage link) throws RestClientException {
    
    // construct request url
    String url = cluster + "/access/accounts/created?token=" + token;

    // construct rest post
    RestTemplate rest = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<LinkMessage> request = new HttpEntity<LinkMessage>(link, headers);
    return rest.postForObject(url, request, EmigoToken.class);
  }

  private EmigoToken attachAccount(String base, String emigoId, LinkMessage link, String pass) throws RestClientException {
  
    // construct request url
    String url = base + "/access/accounts/attached?pass=" + pass + "&emigoId=" + emigoId;

    // construct rest post
    RestTemplate rest = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<LinkMessage> request = new HttpEntity<LinkMessage>(link, headers);
    return rest.postForObject(url, request, EmigoToken.class);
  }

  private UserEntry setToken(EmigoToken emigo) throws RestClientException {

    // construct request url
    String token = configService.getServerStringValue(SC_APP_TOKEN, null);
    String base = configService.getServerStringValue(SC_APP_NODE, null);
    String url = base + "/access/services/tokens?token=" + token;

    // construct rest post
    RestTemplate rest = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<EmigoToken> request = new HttpEntity<EmigoToken>(emigo, headers);
    return rest.postForObject(url, request, UserEntry.class);
  }

  private EmigoMessage getMessage(String emigoId, String handle, String registry) throws RestClientException, IllegalArgumentException {
   
    // construct rquest url
    String url = registry + "/emigo/messages?";
    if(emigoId != null) {
      url += "emigoId=" + emigoId;
    }
    else if(handle != null) {
      url += "handle=" + handle;
    }
    else {
      throw new IllegalArgumentException("account not specified");
    }
  
    // retrieve message from registry
    RestTemplate rest = new RestTemplate();
    return rest.getForObject(url, EmigoMessage.class);    
  }

  @Transactional
  public EmigoLogin attach(String emigoId, String node, String passToken) 
      throws InvalidParameterException, NotFoundException, IOException, RestClientException, Exception {
 
    Long cur = Instant.now().getEpochSecond();

    // retrieve attach link
    LinkMessage link = getAttachLink(emigoId);

    // attach entry
    EmigoToken token = attachAccount(node, emigoId, link, passToken);
    Emigo emigo = EmigoUtil.getObject(token.getEmigo());

    // complete connection
    UserEntry user = setToken(token);
    String accountToken = user.getAccountToken();
    String serviceToken = user.getServiceToken();

    // validate emigo 
    if(!user.getEmigoId().equals(emigo.getEmigoId())) {
      throw new InvalidParameterException("invalid user id");
    }

    // add new entry
    String tok = PasswordUtil.token();
    Account act = accountRepository.findOneByEmigoId(user.getEmigoId());
    if(act == null) {
      act = new Account(emigo, tok, accountToken, serviceToken, cur);
    }
    else {
      act.update(emigo, tok, accountToken, serviceToken, cur);
    }
    act = accountRepository.save(act);
    Contact contact = act;

    // construct response
    NodeConnection account = new NodeConnection();
    account.setEmigoId(contact.getEmigoId());
    account.setNode(contact.getNode());
    account.setHandle(contact.getHandle());
    account.setRegistry(contact.getRegistry());
    account.setToken(accountToken);
    NodeConnection service = new NodeConnection();
    //service.setEmigoId(configService.getServerStringValue(SC_APP_EMIGO, null));
    service.setNode(configService.getServerStringValue(SC_APP_NODE, null));
    //service.setHandle(configService.getServerStringValue(SC_EMIGO_HANDLE, null));
    service.setRegistry(configService.getServerStringValue(SC_EMIGO_REGISTRY, null));
    service.setToken(serviceToken);
    EmigoLogin login = new EmigoLogin();
    login.setAccount(account);
    login.setService(service);
    login.setToken(tok);
    return login;
  }  

  private Emigo getEmigo(Contact contact) {
    Emigo emigo = new Emigo();
    emigo.setEmigoId(contact.getEmigoId());
    emigo.setName(contact.getName());
    emigo.setDescription(contact.getDescription());
    emigo.setLogo(contact.getLogo());
    emigo.setLocation(contact.getLocation());
    emigo.setNode(contact.getNode());
    emigo.setRegistry(contact.getRegistry());
    emigo.setRevision(contact.getRevision());
    emigo.setVersion(contact.getVersion());
    emigo.setHandle(contact.getHandle());
    return emigo;
  }

  @Transactional
  public Emigo update(Account act, String registry, Integer revision) 
      throws IllegalArgumentException, RestClientException, Exception {

    // nothing to do if no new message expected
    if(revision != null && revision <= act.getRevision()) {
      return getEmigo(act);
    }
  
    // retrieve message
    EmigoMessage msg = getMessage(act.getEmigoId(), null, registry);

    // decode message
    Emigo emigo = EmigoUtil.getObject(msg);

    // dont update if registry doesnt match
    if(registry.equals(emigo.getRegistry()) == false) {
      return getEmigo(act);
    }

    // apply updated profile
    act.update(emigo);
    return getEmigo(accountRepository.save(act));
  }

}

