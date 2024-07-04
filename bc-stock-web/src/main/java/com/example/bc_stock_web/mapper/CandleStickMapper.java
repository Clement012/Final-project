package com.example.bc_stock_web.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.example.bc_stock_web.dto.CandleStickDTO;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.model.HistoryData.Quote;
import com.example.bc_stock_web.model.HistoryData.Result;

@Component
public class CandleStickMapper {
  public CandleStickDTO map(HistoryDataEntity entity){
    // List<Long> timestamps = result.getTimestamp();
    // List<Quote> quotes = result.getIndicators().getQuote();
    // for (Long timestamp : timestamps){
      
    // }
    return CandleStickDTO.builder()
       .date(entity.getDate())
       .timestamp(entity.getTimestamp())
       .open(entity.getOpen())
       .low(entity.getLow())
       .high(entity.getHigh())
       .close(entity.getClose())
       .volume(entity.getVolume())
       .build();
  }
}
