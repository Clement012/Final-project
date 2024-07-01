package com.example.sb.bc_yahoo_finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceHKE;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceTCL;
import com.example.sb.bc_yahoo_finance.service.impl.Try;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class Scheduler {

  @Autowired
  private StockDataServiceHKE stockDataServiceHKE;

  @Autowired
  private StockDataServiceTCL stockDataServiceTCL;

  @Scheduled(cron = "0 */5 9-16 ? * MON-FRI")
  // (cron = "0 5 * * * *")
  public void getAndSaveStockData(){
    stockDataServiceHKE.getResponse();
    stockDataServiceHKE.saveStockData();
    stockDataServiceTCL.getResponse();
    stockDataServiceTCL.saveStockData();
  }

  // @Scheduled(cron = "0 55 8 ? * MON-FRI")
  // public void clearData(){
  //   stockDataService.deleteData();
  // }
}
