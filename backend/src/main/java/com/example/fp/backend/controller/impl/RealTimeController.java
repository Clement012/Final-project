package com.example.fp.backend.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.RestController;
import com.example.fp.backend.controller.RealTimeOperation;
import com.example.fp.backend.dto.BidAskDTO;
import com.example.fp.backend.dto.StockDTO;
import com.example.fp.backend.entity.RealTimeEntity;
import com.example.fp.backend.mapper.BidAskMapper;
import com.example.fp.backend.mapper.StockMapper;
import com.example.fp.backend.model.InstantData;
import com.example.fp.backend.model.InstantData.QuoteResponse;
import com.example.fp.backend.model.InstantData.QuoteResponse.Result;
import com.example.fp.backend.service.RealTimeService;

@RestController
public class RealTimeController implements RealTimeOperation{

  @Autowired
  private RealTimeService realTimeService;

  @Autowired
  private BidAskMapper bidAskmapper;

  @Autowired
  private StockMapper stockMapper;

  @Override
  public List<StockDTO> getDataList(){
    // return realTimeService.getDataList();
    List<InstantData> datas = realTimeService.getInstant();
    List<StockDTO> dto = new ArrayList<>();
    for (InstantData data : datas) {
            Result[] results = data.getQuoteResponse().getResult();
                List<StockDTO> stock = Arrays.stream(results)
                    .map(r -> stockMapper.map(r)) 
                    .collect(Collectors.toList());
                dto.addAll(stock);
            
    }
    return dto;
 }
  
  @Override
  public List<InstantData> getInstant(){
    return realTimeService.getInstant();
  }

  @Override
  public List<BidAskDTO> getBidAsks(String symbol){
    List<InstantData> instantdatas = realTimeService.getInstant();
    List<BidAskDTO> bidasks = new ArrayList<>();
    for (InstantData instant : instantdatas) {
        List<BidAskDTO> instantBidAsks = 
                  Arrays.stream(instant.getQuoteResponse().getResult())
                     .filter(r -> r.getSymbol().equals(symbol))
                     .map(r -> bidAskmapper.map(r))
                     .collect(Collectors.toList());
        bidasks.addAll(instantBidAsks);
    }
    return bidasks;
  }

  @Override
  public List<RealTimeEntity> getPrice(String symbol) throws NotFoundException{
    return realTimeService.getRealTime(symbol);
  }
  @Override
  public List<RealTimeEntity> getFive(String symbol) throws NotFoundException{
    return realTimeService.calculateMA(symbol);
  }
}
