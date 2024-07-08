package com.example.bc_stock_web.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.RestController;
import com.example.bc_stock_web.controller.HistoryOperation;
import com.example.bc_stock_web.dto.CandleStickDTO;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.mapper.CandleStickMapper;
import com.example.bc_stock_web.service.HistoryDataService;
import com.example.bc_stock_web.model.HistoryData;
import com.example.bc_stock_web.repository.HistoryDataRepository;

@RestController
public class HistoryController implements HistoryOperation {

  @Autowired
  private HistoryDataRepository historyDataRepository;

  @Autowired
  private CandleStickMapper candleStickMapper;

  @Autowired
  private HistoryDataService historyDataService;

  // @Override
  // public HistoryData getHistoryData(){
  //   return historyService.getHistoryData();
  // }

  @Override
  public List<CandleStickDTO> getPrice(){
    return historyDataRepository.findAll().stream()
        .map(h -> candleStickMapper.map(h))
        .collect(Collectors.toList());
  }
  
  @Override
  public List<HistoryData> getDataList(){
    return historyDataService.getDataList();
  }

  @Override
  public List<HistoryDataEntity> getHistoryEntity(String symbol)throws NotFoundException{
    return historyDataService.getHistoryEntity(symbol);
  }
}
