package com.example.bc_stock_web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.bc_stock_web.service.HistoryDataService;

@Component
public class Runner implements CommandLineRunner{

  @Autowired
  private HistoryDataService historyDataService;

  @Override
  public void run(String... args){
      // multiDataService.clearData();
      // System.out.println("reset");
      historyDataService.saveData();
      System.out.println("ok");
  }
}
