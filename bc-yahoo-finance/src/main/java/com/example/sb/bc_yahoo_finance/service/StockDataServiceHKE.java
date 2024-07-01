package com.example.sb.bc_yahoo_finance.service;

import java.util.List;
import com.example.sb.bc_yahoo_finance.model.Response;


//0388
public interface StockDataServiceHKE {
  
  List<Response> getResponse(); 

  void saveStockData();

  void deleteData();
}
