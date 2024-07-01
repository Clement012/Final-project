package com.example.sb.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.sb.bc_yahoo_finance.dto.ResultDTO;
import com.example.sb.bc_yahoo_finance.model.Response;

public interface StockOperationTCL {
  
  //0700
  @GetMapping(value = "/symbol/tcl")
  List<Response> getResponse();  // get all data from one source

  @GetMapping(value = "/stockdata/tcl")
  List<ResultDTO> getStockData(); // get 8 attribute
}
