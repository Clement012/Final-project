package com.example.bc_stock_web.config;

import org.springframework.stereotype.Component;
import com.example.bc_stock_web.controller.RealTimeOperation;
import com.example.bc_stock_web.service.RealTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class Scheduler {
  
  @Autowired
  private RealTimeOperation realTimeOperation;

  @Autowired
  private RealTimeService realTimeService;
  
  @Scheduled(cron = "0 0/5 9-16 ? * MON-FRI")
  public void getData(){
    realTimeOperation.getDataList();
  }

  @Scheduled(cron = "0 0/5 9-16 ? * MON-FRI")
  public void saveData(){
    realTimeService.savePrice();
    System.out.println("updated");
  }

  @Scheduled(cron = "0 */10 9-16 ? * MON-FRI")
  public void saveBidAsk(){
    realTimeService.saveBidAsk();
  }

  @Scheduled(cron = "0 55 8 ? * MON-FRI")  //clear every date
  public void deleteData(){
    realTimeService.clearData();
  }

  
}
