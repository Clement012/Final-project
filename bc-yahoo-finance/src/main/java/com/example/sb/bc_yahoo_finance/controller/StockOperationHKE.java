package com.example.sb.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.sb.bc_yahoo_finance.dto.ResultDTO;
import com.example.sb.bc_yahoo_finance.model.Symbol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.example.sb.bc_yahoo_finance.model.Response;

public interface StockOperationHKE {
  
  //0388
  @GetMapping(value = "/symbol")
  List<Symbol> getSymbols() throws JsonProcessingException;

  @GetMapping(value = "/stock/hke")
  List<Response> getResponse();  // get all data from one source

  @GetMapping(value = "/stockdata/hke")
  List<ResultDTO> getStockData(); // get 8 attribute

  
}
