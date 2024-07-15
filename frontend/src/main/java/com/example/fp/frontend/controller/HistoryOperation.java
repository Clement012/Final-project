package com.example.fp.frontend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.fp.frontend.model.history.CandleStick;
import com.example.fp.frontend.model.history.HistoryData;
import com.example.fp.frontend.model.history.HistoryEntity;

public interface HistoryOperation {
  
  @GetMapping (value = "/candle")
  List<CandleStick> getPrice();

  @GetMapping (value = "/historydatalist")
  List<HistoryData> getDataList();

  @GetMapping (value = "/historyentity/{symbol}")
  List<HistoryEntity> getHistoryEntity(@PathVariable String symbol);

  static String test(){
    return "Hello";
  }
}
