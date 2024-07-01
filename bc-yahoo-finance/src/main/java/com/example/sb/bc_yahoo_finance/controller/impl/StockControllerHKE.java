package com.example.sb.bc_yahoo_finance.controller.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.sb.bc_yahoo_finance.controller.StockOperationHKE;
import com.example.sb.bc_yahoo_finance.dto.ResultDTO;
import com.example.sb.bc_yahoo_finance.mapper.ResultMapper;
import com.example.sb.bc_yahoo_finance.model.Symbol;
import com.example.sb.bc_yahoo_finance.model.Response.QuoteResponse;
import com.example.sb.bc_yahoo_finance.model.Response.QuoteResponse.Result;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceHKE;
import com.example.sb.bc_yahoo_finance.service.SymbolService;
import com.example.sb.bc_yahoo_finance.service.impl.Try;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.example.sb.bc_yahoo_finance.model.Response;

@RestController
public class StockControllerHKE implements StockOperationHKE {

  @Autowired
  private SymbolService symbolService;

  @Autowired
  private StockDataServiceHKE stockDataServiceHKE;

  @Autowired
  private ResultMapper resultMapper;
  
  @Override
  public List<Symbol> getSymbols()throws JsonProcessingException{
    return symbolService.getSymbols();
  }

  @Override
  public List<Response> getResponse(){
    return stockDataServiceHKE.getResponse();
  }

  @Override
  public List<ResultDTO> getStockData(){
    List<Response> responses = stockDataServiceHKE.getResponse();
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
