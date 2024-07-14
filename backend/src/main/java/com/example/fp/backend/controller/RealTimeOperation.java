package com.example.fp.backend.controller;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.fp.backend.dto.BidAskDTO;
import com.example.fp.backend.dto.StockDTO;
import com.example.fp.backend.entity.RealTimeEntity;
import com.example.fp.backend.model.InstantData;

public interface RealTimeOperation {

  @GetMapping(value = "/datalist")
  List<StockDTO> getDataList();
  
  @GetMapping(value = "/instant")
  List<InstantData> getInstant();  

  @GetMapping(value = "/bidask/{symbol}")
  List<BidAskDTO> getBidAsks(@PathVariable String symbol);

  @GetMapping(value = "/fiveminute/{symbol}")
  List<RealTimeEntity> getPrice(@PathVariable String symbol) throws NotFoundException;

  @GetMapping(value = "/ma/{symbol}")
  List<RealTimeEntity> getFive(@PathVariable String symbol) throws NotFoundException;
}


