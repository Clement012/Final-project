package com.example.fp.backend.mapper;

import org.springframework.stereotype.Component;
import com.example.fp.backend.dto.CandleStickDTO;
import com.example.fp.backend.entity.HistoryDataEntity;

@Component
public class CandleStickMapper {
  public CandleStickDTO map(HistoryDataEntity entity){
    return CandleStickDTO.builder()
       .date(String.valueOf(entity.getDate()))
       .timestamp(entity.getTimestamp())
       .open(entity.getOpen())
       .low(entity.getLow())
       .high(entity.getHigh())
       .close(entity.getClose())
       .volume(entity.getVolume())
       .build();
  }
}
