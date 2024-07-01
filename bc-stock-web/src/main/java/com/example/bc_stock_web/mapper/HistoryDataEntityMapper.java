package com.example.bc_stock_web.mapper;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.model.HistoryData;
import com.example.bc_stock_web.model.HistoryData.AdjClose;
import com.example.bc_stock_web.model.HistoryData.Chart;
import com.example.bc_stock_web.model.HistoryData.Meta;
import com.example.bc_stock_web.model.HistoryData.Quote;
import com.example.bc_stock_web.model.HistoryData.Result;

@Component
public class HistoryDataEntityMapper {
  public HistoryDataEntity map(HistoryData historyData){
    HistoryDataEntity entity = new HistoryDataEntity();
    List<Result> results = historyData.getChart().getResult();
     for (Result result : results){
      String symbol = result.getMeta().getSymbol();
      entity.setSymbol(symbol); 
      List<Quote> quotes = result.getIndicators().getQuote();
       for (Quote quote : quotes){
        List<Double> opens = quote.getOpen();
        List<Double> closes = quote.getClose();
        List<Double> lows = quote.getLow();
        List<Double> highs = quote.getHigh();
        List<Long> volumes = quote.getVolume();
        for (Double open : opens){
          entity.setOpen(open);
        }
        for (Double close : closes){
          entity.setClose(close);
        }
        for (Double low : lows){
          entity.setLow(low);
        }
        for(Double high : highs){
          entity.setHigh(high);
        }
        for(Long volume : volumes){
          entity.setVolume(volume);
        }
       }
      List<Long> timestamps = result.getTimestamp();
       for (Long timestamp : timestamps){
        entity.setTimestamp(timestamp);
       }
       List<AdjClose> adjClosesss = result.getIndicators().getAdjclose();
       for (AdjClose adjClosess : adjClosesss){
        List<Double> adjCloses = adjClosess.getAdjclose();
          for (Double adjClose : adjCloses){
            entity.setAdjclose(adjClose);
          }
        }
       }
    entity.setType("Daily");
    entity.setDate(null);
    return entity;
  }
}
