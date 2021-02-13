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
        if(config.getConfigId().equals(SC_REGION)) {
        appConfig.setRegion(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_SOURCE_EMAIL)) {
        appConfig.setSourceEmail(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_STAT_TOKEN)) {
        appConfig.setStatToken(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_ACCESS_ID)) {
        appConfig.setAccessId(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_ACCESS_KEY)) {
        appConfig.setAccessKey(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_CONSOLE_TOKEN)) {
        appConfig.setConsoleToken(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_APP_NODE)) {
        appConfig.setAppNode(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_APP_EMIGO)) {
        appConfig.setAppEmigo(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_APP_TOKEN)) {
        appConfig.setAppToken(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_CLUSTER_URL)) {
        appConfig.setClusterUrl(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_CLUSTER_TOKEN)) {
        appConfig.setClusterToken(config.getStringValue());
      }
      else if(config.getConfigId().equals(SC_CLUSTER_STATUS)) {
        appConfig.setSlotToken(config.getStringValue());
      }
    }
    return appConfig;
  }

  public void setConfig(AppConfig config) {
    List<ServerConfig> configs = new ArrayList<ServerConfig>();
      
    ServerConfig slotToken = configRepository.findOneByConfigId(SC_CLUSTER_STATUS);
    if(slotToken == null) {
      slotToken = new ServerConfig();
      slotToken.setConfigId(SC_CLUSTER_STATUS);
    }
    slotToken.setStringValue(config.getSlotToken());
    configs.add(slotToken);

    ServerConfig clusterToken = configRepository.findOneByConfigId(SC_CLUSTER_TOKEN);
    if(clusterToken == null) {
      clusterToken = new ServerConfig();
      clusterToken.setConfigId(SC_CLUSTER_TOKEN);
    }
    clusterToken.setStringValue(config.getClusterToken());
    configs.add(clusterToken);
   
    ServerConfig clusterUrl = configRepository.findOneByConfigId(SC_CLUSTER_URL);
    if(clusterUrl == null) {
      clusterUrl = new ServerConfig();
      clusterUrl.setConfigId(SC_CLUSTER_URL);
    }
    clusterUrl.setStringValue(config.getClusterUrl());
    configs.add(clusterUrl);
   
    ServerConfig appToken = configRepository.findOneByConfigId(SC_APP_TOKEN);
    if(appToken == null) {
      appToken = new ServerConfig();
      appToken.setConfigId(SC_APP_TOKEN);
    }
    appToken.setStringValue(config.getAppToken());
    configs.add(appToken);
   
    ServerConfig appEmigo = configRepository.findOneByConfigId(SC_APP_EMIGO);
    if(appEmigo == null) {
      appEmigo = new ServerConfig();
      appEmigo.setConfigId(SC_APP_EMIGO);
    }
    appEmigo.setStringValue(config.getAppEmigo());
    configs.add(appEmigo);
   
    ServerConfig appNode = configRepository.findOneByConfigId(SC_APP_NODE);
    if(appNode == null) {
      appNode = new ServerConfig();
      appNode.setConfigId(SC_APP_NODE);
    }
    appNode.setStringValue(config.getAppNode());
    configs.add(appNode);
   
    ServerConfig statToken = configRepository.findOneByConfigId(SC_STAT_TOKEN);
    if(statToken == null) {
      statToken = new ServerConfig();
      statToken.setConfigId(SC_STAT_TOKEN);
    }
    statToken.setStringValue(config.getStatToken());
    configs.add(statToken);
   
    ServerConfig consoleToken = configRepository.findOneByConfigId(SC_CONSOLE_TOKEN);
    if(consoleToken == null) {
      consoleToken = new ServerConfig();
      consoleToken.setConfigId(SC_CONSOLE_TOKEN);
    }
    consoleToken.setStringValue(config.getConsoleToken());
    configs.add(consoleToken);
   
    ServerConfig accessKey = configRepository.findOneByConfigId(SC_ACCESS_KEY);
    if(accessKey == null) {
      accessKey = new ServerConfig();
      accessKey.setConfigId(SC_ACCESS_KEY);
    }
    accessKey.setStringValue(config.getAccessKey());
    configs.add(accessKey);
   
    ServerConfig accessId = configRepository.findOneByConfigId(SC_ACCESS_ID);
    if(accessId == null) {
      accessId = new ServerConfig();
      accessId.setConfigId(SC_ACCESS_ID);
    }
    accessId.setStringValue(config.getAccessId());
    configs.add(accessId);
   
    ServerConfig email = configRepository.findOneByConfigId(SC_SOURCE_EMAIL);
    if(email == null) {
      email = new ServerConfig();
      email.setConfigId(SC_SOURCE_EMAIL);
    }
    email.setStringValue(config.getSourceEmail());
    configs.add(email);
   
    ServerConfig region = configRepository.findOneByConfigId(SC_REGION);
    if(region == null) {
      region = new ServerConfig();
      region.setConfigId(SC_REGION);
    }
    region.setStringValue(config.getRegion());
    configs.add(region);

    configRepository.save(configs);

    return;
  }
}

