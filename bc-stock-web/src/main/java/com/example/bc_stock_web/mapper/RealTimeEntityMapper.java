package com.example.bc_stock_web.mapper;

import org.springframework.stereotype.Component;
import com.example.bc_stock_web.entity.RealTimeEntity;
import java.time.LocalDateTime;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import java.time.LocalDate;

@Component
public class RealTimeEntityMapper {
  
  public RealTimeEntity map(Result result){
    RealTimeEntity entity = new RealTimeEntity();
    entity.setTime(LocalDateTime.now());
    entity.setRegularMarketPrice(result.getRegularMarketPrice());
    entity.setAveragePrice(0);
    entity.setDate(LocalDate.now());
    entity.setType("5-MINS");
    entity.setSymbol(result.getSymbol());
    entity.setTimestamp(System.currentTimeMillis());

    return entity;
  }
}
