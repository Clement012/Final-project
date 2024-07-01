package com.example.sb.bc_yahoo_finance.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sb.bc_yahoo_finance.controller.StockOperationTCL;
import com.example.sb.bc_yahoo_finance.dto.ResultDTO;
import com.example.sb.bc_yahoo_finance.mapper.ResultMapper;
import com.example.sb.bc_yahoo_finance.model.Response;
import com.example.sb.bc_yahoo_finance.model.Response.QuoteResponse;
import com.example.sb.bc_yahoo_finance.model.Response.QuoteResponse.Result;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceTCL;

@RestController
public class StockControllerTCL implements StockOperationTCL{
  
  @Autowired
  private StockDataServiceTCL stockDataServiceTCL;

  @Autowired
  private ResultMapper resultMapper;

  @Override
  public List<Response> getResponse(){
    return stockDataServiceTCL.getResponse();
  } 

  @Override
  public List<ResultDTO> getStockData(){
    List<Response> responses = stockDataServiceTCL.getResponse();
    List<ResultDTO> resultDTOs = new ArrayList<>();
    for (Response response : responses) {
        QuoteResponse quoteResponse = response.getQuoteResponse();
        if (quoteResponse != null) {
            Result[] results = quoteResponse.getResult();
            if (results != null) {
                List<ResultDTO> dtoList = Arrays.stream(results)
                    .map(r -> resultMapper.map(r)) 
                    .collect(Collectors.toList());
                resultDTOs.addAll(dtoList);
            }
        }
    }
    return resultDTOs;
  } 
}
