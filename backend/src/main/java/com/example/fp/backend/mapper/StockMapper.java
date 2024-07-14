package com.example.fp.backend.mapper;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.example.fp.backend.dto.StockDTO;
import com.example.fp.backend.model.InstantData.QuoteResponse.Result;
import lombok.Builder;

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
      .ask(result.getAsk())
      .timeNow(String.valueOf(LocalDateTime.now()))
      .longName(result.getLongName())
      .regularMarketChange(result.getRegularMarketChange())
      .regularMarketVolume(result.getRegularMarketVolume())
      .marketCap(result.getMarketCap())
      .build();
  }
}
