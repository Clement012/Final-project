package com.example.bc_stock_web.mapper;

import org.springframework.stereotype.Component;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;

@Component
public class StockBidAskMapper {
  public StockBidAskDTO map(Result result){
    return StockBidAskDTO.builder()
      .ask(result.getAsk())
      .bid(result.getBid())
      .build();
  }
}
