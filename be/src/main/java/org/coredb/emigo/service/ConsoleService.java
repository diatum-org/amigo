package org.coredb.emigo.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.coredb.emigo.NameRegistry.*;
import org.coredb.emigo.model.AppConfig;
import org.coredb.emigo.jpa.entity.ServerConfig;
import org.coredb.emigo.jpa.repository.ServerConfigRepository;

@Service
public class ConsoleService {

  @Autowired
  ServerConfigRepository configRepository;

  @Autowired
  ConfigService configService;

  public boolean checkAccess(String t) {
    String token = configService.getServerStringValue(SC_CONSOLE_TOKEN, null);
    if(t == null || token == null) {
      return false;
    }
    return token.equals(t);
  }

  public AppConfig getConfig() {
    AppConfig appConfig = new AppConfig();
    List<ServerConfig> configs = configRepository.findAll();
    for(ServerConfig config: configs) {
      if(config.getConfigId().equals(SC_CONSOLE_TOKEN)) {
        appConfig.setConsoleToken(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_APP_NODE)) {
        appConfig.setAppNode(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_APP_TOKEN)) {
        appConfig.setAppToken(config.getStringValue());
      }
    }
    return appConfig;
  }

  public void setConfig(AppConfig config) {
    List<ServerConfig> configs = new ArrayList<ServerConfig>();
      
    ServerConfig appToken = configRepository.findOneByConfigId(SC_APP_TOKEN);
    if(appToken == null) {
      appToken = new ServerConfig();
      appToken.setConfigId(SC_APP_TOKEN);
    }
    appToken.setStringValue(config.getAppToken());
    configs.add(appToken);
   
    ServerConfig appNode = configRepository.findOneByConfigId(SC_APP_NODE);
    if(appNode == null) {
      appNode = new ServerConfig();
      appNode.setConfigId(SC_APP_NODE);
    }
    appNode.setStringValue(config.getAppNode());
    configs.add(appNode);
   
    ServerConfig consoleToken = configRepository.findOneByConfigId(SC_CONSOLE_TOKEN);
    if(consoleToken == null) {
      consoleToken = new ServerConfig();
      consoleToken.setConfigId(SC_CONSOLE_TOKEN);
    }
    consoleToken.setStringValue(config.getConsoleToken());
    configs.add(consoleToken);
   
    configRepository.save(configs);
    return;
  }
}

