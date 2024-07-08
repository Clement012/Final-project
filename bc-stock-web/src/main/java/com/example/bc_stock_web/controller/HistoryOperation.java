package com.example.bc_stock_web.controller;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.bc_stock_web.dto.CandleStickDTO;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.model.HistoryData;

public interface HistoryOperation {

  @GetMapping (value = "/candle")
  List<CandleStickDTO> getPrice();

  @GetMapping (value = "/historydatalist")
  List<HistoryData> getDataList();

  @GetMapping (value = "/historyentity/{symbol}")
  List<HistoryDataEntity> getHistoryEntity(@PathVariable String symbol) throws NotFoundException;

  // public static String test(){
  //   return "hello";
  // }

}
