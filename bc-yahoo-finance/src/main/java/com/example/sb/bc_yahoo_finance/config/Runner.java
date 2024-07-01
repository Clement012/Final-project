package com.example.sb.bc_yahoo_finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceHKE;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceTCL;
import com.example.sb.bc_yahoo_finance.service.SymbolService;
import com.example.sb.bc_yahoo_finance.service.impl.Try;


@Configuration
public class Runner implements CommandLineRunner{

  @Autowired
  private SymbolService stockService;

  @Autowired
  private StockDataServiceHKE stockDataServiceHKE;

  @Autowired
  private StockDataServiceTCL stockDataServiceTCL;

  @Override
  public void run(String... args) throws Exception {
    stockService.clearSymbols();
    stockService.addSymbol();
    stockDataServiceHKE.saveStockData();
    stockDataServiceTCL.saveStockData();
  }
  
}
