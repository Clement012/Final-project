package com.example.sb.bc_yahoo_finance.service;

import java.util.List;
import com.example.sb.bc_yahoo_finance.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

//0700
public interface StockDataServiceTCL {
  
  List<Response> getResponse(); 

  void saveStockData();

  void deleteData();
}
