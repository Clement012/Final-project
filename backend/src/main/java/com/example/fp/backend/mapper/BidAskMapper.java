package com.example.fp.backend.mapper;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.example.fp.backend.dto.BidAskDTO;
import com.example.fp.backend.model.InstantData.QuoteResponse.Result;

@Component
public class BidAskMapper {
  public BidAskDTO map(Result result){
    return BidAskDTO.builder()
           .symbol(result.getSymbol())
           .ask(result.getAsk())
           .bid(result.getBid())
           .build();
  }
}
