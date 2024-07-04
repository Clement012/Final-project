package com.example.bc_stock_web.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
  public List<HistoryDataEntity> map(Result result){
    List<HistoryDataEntity> entities = new ArrayList<>();
    List<Long> timestamps = result.getTimestamp();
    Quote quotes = result.getIndicators().getQuote().get(0);
    AdjClose adjCloses = result.getIndicators().getAdjclose().get(0);
    
    timestamps.forEach(t ->{
      int index = timestamps.indexOf(t);
      HistoryDataEntity entity = new HistoryDataEntity();
      entity.setTimestamp(t);
      entity.setDate(LocalDate.ofInstant(Instant.ofEpochMilli(t*1000L), ZoneId.systemDefault()));
      entity.setOpen(quotes.getOpen().get(index));
      entity.setClose(quotes.getClose().get(index));
      entity.setLow(quotes.getLow().get(index));
      entity.setHigh(quotes.getHigh().get(index));
      entity.setVolume(quotes.getVolume().get(index));
      entity.setAdjclose(adjCloses.getAdjclose().get(index));
      entity.setSymbol(result.getMeta().getSymbol());
      entity.setType("Daily");
      // entity.setRegularMarketPrice(result.getMeta().getRegularMarketPrice());
      entities.add(entity);
    });

    return entities;
    // entity.setSymbol(result.getMeta().getSymbol());
    // entity.setType("Daily");
    // entity.setTimestamp(result.getTimestamp());
    // entity.setDate(null);
    // entity.setOpen();
    // entity.setClose(0);
    // entity.setLow(0);
    // entity.setHigh(0);
    // entity.setVolume(null);
    // entity.setAdjclose(result.getIndicators().getAdjclose());
    // return entity;
  }
  // public HistoryDataEntity map(HistoryData historyData){
  //   HistoryDataEntity entity = new HistoryDataEntity();
  //   List<Result> results = historyData.getChart().getResult();
  //    for (Result result : results){
  //     String symbol = result.getMeta().getSymbol();
  //     entity.setSymbol(symbol); 
  //     List<Quote> quotes = result.getIndicators().getQuote();
  //      for (Quote quote : quotes){
  //       List<Double> opens = quote.getOpen();
  //       List<Double> closes = quote.getClose();
  //       List<Double> lows = quote.getLow();
  //       List<Double> highs = quote.getHigh();
  //       List<Long> volumes = quote.getVolume();
  //       for (Double open : opens){
  //         entity.setOpen(open);
  //       }
  //       for (Double close : closes){
  //         entity.setClose(close);
  //       }
  //       for (Double low : lows){
  //         entity.setLow(low);
  //       }
  //       for(Double high : highs){
  //         entity.setHigh(high);
  //       }
  //       for(Long volume : volumes){
  //         entity.setVolume(volume);
  //       }
  //      }
  //     List<Long> timestamps = result.getTimestamp();
  //      for (Long timestamp : timestamps){
  //       entity.setTimestamp(timestamp);
  //      }
  //      List<AdjClose> adjClosesss = result.getIndicators().getAdjclose();
  //      for (AdjClose adjClosess : adjClosesss){
  //       List<Double> adjCloses = adjClosess.getAdjclose();
  //         for (Double adjClose : adjCloses){
  //           entity.setAdjclose(adjClose);
  //         }
  //       }
  //      }
  //   entity.setType("Daily");
  //   entity.setDate(null);
  //   return entity;
  // }
}

// 
