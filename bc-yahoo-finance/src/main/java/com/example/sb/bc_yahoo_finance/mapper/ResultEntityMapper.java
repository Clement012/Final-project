package com.example.sb.bc_yahoo_finance.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.example.sb.bc_yahoo_finance.dto.ResultEntityDTO;
import com.example.sb.bc_yahoo_finance.entity.ResultEntity;
import com.example.sb.bc_yahoo_finance.model.Response.QuoteResponse.Result;

@Component
public class ResultEntityMapper {
  public ResultEntity map(Result result){

    ResultEntity resultEntity = new ResultEntity();
    resultEntity.setSymbol(result.getSymbol());
    resultEntity.setRegularMarketUnix(String.valueOf(System.currentTimeMillis()));
    resultEntity.setRegularMarketPrice(result.getRegularMarketPrice());
    resultEntity.setRegularMarketChangePercent(result.getRegularMarketChangePercent());
    resultEntity.setBid(result.getBid());
    resultEntity.setAsk(result.getAsk());
    resultEntity.setBidSize(result.getBidSize());
    resultEntity.setAskSize(result.getAskSize());
    resultEntity.setApiDateTime(LocalDateTime.now());
    resultEntity.setSystemDate(LocalDate.now());

    return resultEntity;
  }
}