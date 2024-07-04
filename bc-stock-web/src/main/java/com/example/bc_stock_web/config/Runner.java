package com.example.bc_stock_web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.bc_stock_web.service.HistoryService;
import com.example.bc_stock_web.service.MultiDataService;

@Component
public class Runner implements CommandLineRunner{

  // @Autowired
  // private HistoryService historyService;

  @Autowired
  private MultiDataService multiDataService;


  @Override
  public void run(String... args){
      // historyService.saveData();
      // multiDataService.clearData();
      // System.out.println("reset");
      multiDataService.saveData();
      System.out.println("ok");
  }
}
