package com.example.fp.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.fp.backend.service.RealTimeService;

@Component
public class Scheduler {

  @Autowired
  private RealTimeService realTimeService;

  @Scheduled(cron = "0 0/5 9-16 ? * MON-FRI", zone = "Asia/Hong_Kong")
  public void get(){
    realTimeService.getInstant();
    System.out.println("Instantgetok");
  }
  
  @Scheduled(cron = "0 0/5 9-16 ? * MON-FRI", zone = "Asia/Hong_Kong")
  public void save(){
    realTimeService.saveInstant();
    System.out.println("Instantsaveok");
  }

  @Scheduled(cron = "0 55 8 ? * MON-FRI", zone = "Asia/Hong_Kong")
  public void delete(){
    realTimeService.clear();
  }
}
