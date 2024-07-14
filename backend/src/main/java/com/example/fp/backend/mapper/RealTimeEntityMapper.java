package com.example.fp.backend.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.example.fp.backend.entity.RealTimeEntity;
import com.example.fp.backend.model.InstantData.QuoteResponse.Result;

@Component
public class RealTimeEntityMapper {
  public RealTimeEntity map(Result result){
    RealTimeEntity entity = new RealTimeEntity();
    entity.setTime(String.valueOf(LocalDateTime.now()));
    entity.setRegularMarketPrice(result.getRegularMarketPrice());
    entity.setAveragePrice(0);
    entity.setDate(String.valueOf(LocalDate.now()));
    entity.setType("5-MINS");
    entity.setSymbol(result.getSymbol());
    entity.setTimestamp(System.currentTimeMillis());

    return entity;
  }
}
