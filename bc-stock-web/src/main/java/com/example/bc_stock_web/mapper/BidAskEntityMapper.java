package com.example.bc_stock_web.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.example.bc_stock_web.entity.RealTimeEntity;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;

@Component
public class BidAskEntityMapper {
  
  public RealTimeEntity map(Result result){
    RealTimeEntity entity = new RealTimeEntity();
    entity.setTime(LocalDateTime.now());
    entity.setRegularMarketPrice(result.getRegularMarketPrice());
    entity.setAveragePrice(0);
    entity.setDate(LocalDate.now());
    entity.setType("Instant");
    entity.setSymbol(result.getSymbol());
    entity.setTimestamp(System.currentTimeMillis());
    entity.setBid(result.getBid());
    entity.setAsk(result.getAsk());

    return entity;
  }
}
