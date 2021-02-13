package org.coredb.emigo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.coredb.emigo.NameRegistry;
import org.coredb.emigo.service.ConfigService;
import org.coredb.emigo.service.PollNodeService;

@Component
public class ScheduledTasks {

  @Autowired
  private ConfigService configService;

  @Autowired
  private PollNodeService pollService;  

  private long intervalCount = 0;
  private long configInterval = 60;
  private long nodeInterval = 15;

  @Scheduled(fixedRate = 60000)
  public void reportCurrentTime() {
    if(intervalCount % configInterval == 0) {
      configInterval = configService.refresh();
    }
    if(intervalCount % nodeInterval == 0) {
      nodeInterval = pollService.poll();
    }
    intervalCount++;
  }
}

