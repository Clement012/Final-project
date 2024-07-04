package com.example.bc_stock_web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bc_stock_web.dto.CandleStickDTO;
import com.example.bc_stock_web.model.HistoryData;

public interface HistoryOperation {
  
  // @GetMapping (value = "/history")
  // HistoryData getHistoryData();

  @GetMapping (value = "/candle")
  List<CandleStickDTO> getPrice();

  // @GetMapping (value = "/mu")
  // HistoryData getData();

  @GetMapping (value = "/datalist")
  List<HistoryData> getDataList();

}
