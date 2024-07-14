package com.example.fp.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.fp.backend.service.HistoryService;

@Component
public class Runner implements CommandLineRunner{

  @Autowired
  private HistoryService historyService;

  @Override
  public void run(String... args){
      historyService.saveData();
      System.out.println("historyok");
  }
}
