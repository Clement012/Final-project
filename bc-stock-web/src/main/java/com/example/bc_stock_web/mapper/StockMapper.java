package com.example.bc_stock_web.mapper;

import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import lombok.Builder;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
@Builder
public class StockMapper {
  public StockDTO map(Result result){
    return StockDTO.builder()
      .symbol(result.getSymbol())
      .regularMarketUnix(String.valueOf(System.currentTimeMillis())) // 
      .regularMarketPrice(result.getRegularMarketPrice())
      .regularMarketChangePercent(result.getRegularMarketChangePercent())
      .bid(result.getBid())
      .bidSize(result.getBidSize())
      .ask(result.getAsk())
      .askSize(result.getAskSize())
      .timeNow(LocalDateTime.now())
      .build();
  }
}
