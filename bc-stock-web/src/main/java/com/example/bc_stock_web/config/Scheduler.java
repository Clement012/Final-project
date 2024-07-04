package com.example.bc_stock_web.config;

import org.springframework.stereotype.Component;
import com.example.bc_stock_web.controller.StockPriceOperation;
import com.example.bc_stock_web.service.HistoryService;
import com.example.bc_stock_web.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class Scheduler {
  
  @Autowired
  private StockPriceOperation stockPriceOperation;

  @Autowired
  private StockService stockService;

  // @Autowired
  // private HistoryService historyService;
  
  @Scheduled(cron = "0 30-59/5 9 ? * MON-FRI")
  @Scheduled(cron = "0 */5 10-16 ? * MON-FRI")
  public void getData(){
    stockPriceOperation.getStockData();
  }

  @Scheduled(cron = "0 30-59/5 9 ? * MON-FRI")
  @Scheduled(cron = "0 */5 10-16 ? * MON-FRI")
  public void saveData(){
    stockService.savePrice();
    System.out.println("updated");
  }

  @Scheduled(cron = "0 55 8 ? * MON-FRI")  //clear every date
  public void deleteData(){
    stockService.clearData();
  }

  
}
