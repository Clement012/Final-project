package com.example.bc_stock_web.mapper;

import org.springframework.stereotype.Component;
import com.example.bc_stock_web.entity.StockDataEntity;
import java.time.LocalDateTime;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import java.time.LocalDate;

@Component
public class StockDataEntityMapper {
  
  public StockDataEntity map(Result result){
    StockDataEntity entity = new StockDataEntity();
    entity.setTime(LocalDateTime.now());
    entity.setRegularMarketPrice(result.getRegularMarketPrice());
    entity.setAveragePrice(0);
    entity.setDate(LocalDate.now());
    entity.setType("5-MINS");

    return entity;
  }
}
