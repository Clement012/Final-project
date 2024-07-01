package com.example.sb.bc_yahoo_finance.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.sb.bc_yahoo_finance.dto.ResultDTO;
import com.example.sb.bc_yahoo_finance.model.Response;
import com.example.sb.bc_yahoo_finance.model.Response.QuoteResponse.Result;

@Component
public class ResultMapper {
  public ResultDTO map(Result result){
    return ResultDTO.builder()
       .symbol(result.getSymbol())
       .regularMarketUnix(String.valueOf(System.currentTimeMillis())) // 
       .regularMarketPrice(result.getRegularMarketPrice())
       .regularMarketChangePercent(result.getRegularMarketChangePercent())
       .bid(result.getBid())
       .bidSize(result.getBidSize())
       .ask(result.getAsk())
       .askSize(result.getAskSize())
       .build();
  }
}

