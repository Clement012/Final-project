package com.example.fp.frontend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.fp.frontend.model.instant.BidAsk;
import com.example.fp.frontend.model.instant.InstantData;
import com.example.fp.frontend.model.instant.RealTime;
import com.example.fp.frontend.model.instant.Stock;

public interface RealTimeOperation {
  
  @GetMapping(value = "/datalist")
  List<Stock> getDataList();
 
  @GetMapping(value = "/instant")
  List<InstantData> getInstant();  

  @GetMapping(value = "/bidask/{symbol}")
  List<BidAsk> getBidAsks(@PathVariable String symbol);

 @GetMapping(value = "/fiveminute/{symbol}")
  List<RealTime> getPrice(@PathVariable String symbol);

  @GetMapping(value = "/ma/{symbol}")
  List<RealTime> getFive(@PathVariable String symbol);
}
