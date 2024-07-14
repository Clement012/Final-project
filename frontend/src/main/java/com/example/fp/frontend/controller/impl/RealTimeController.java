package com.example.fp.frontend.controller.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.fp.frontend.controller.RealTimeOperation;
import com.example.fp.frontend.model.instant.BidAsk;
import com.example.fp.frontend.model.instant.InstantData;
import com.example.fp.frontend.model.instant.RealTime;
import com.example.fp.frontend.model.instant.Stock;
import com.example.fp.frontend.service.RealTimeService;

@RestController
public class RealTimeController implements RealTimeOperation{
  
  @Autowired
  private RealTimeService realTimeService;

  @Override
  public List<Stock> getDataList(){
    return realTimeService.getStocks();
  }

  @Override
  public List<InstantData> getInstant(){
    return realTimeService.getInstants();
  }

  @Override
  public List<BidAsk> getBidAsks(String symbol){
    return realTimeService.getBidAsks(symbol);
    // List<BidAsk> bidasks = realTimeService.getBidAsks(symbol);
    // List<BidAsk> output = new ArrayList<>();
    // for (BidAsk bidask : bidasks){
    //   if (bidask.getSymbol() == symbol){
    //     output.add(bidask);
    //   }
    // }
    // return output;
  }

  @Override
  public List<RealTime> getPrice(String symbol){
    return realTimeService.getPrice(symbol);
  }

  @Override
  public List<RealTime> getFive(String symbol){
    return realTimeService.getFive(symbol);
  }
}


