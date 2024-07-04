package com.example.bc_stock_web.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.bc_stock_web.controller.HistoryOperation;
import com.example.bc_stock_web.dto.CandleStickDTO;
import com.example.bc_stock_web.mapper.CandleStickMapper;
import com.example.bc_stock_web.service.HistoryService;
import com.example.bc_stock_web.service.MultiDataService;
import com.example.bc_stock_web.service.impl.TestServiceImpl;
import com.example.bc_stock_web.model.HistoryData;
import com.example.bc_stock_web.repository.HistoryDataRepository;

@RestController
public class HistoryController implements HistoryOperation {
  
  // @Autowired
  // private HistoryService historyService;

  @Autowired
  private HistoryDataRepository historyDataRepository;

  @Autowired
  private CandleStickMapper candleStickMapper;

  @Autowired
  private MultiDataService multiDataService;

  // @Autowired
  // private TestServiceImpl testServiceImpl;

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

  // @Override
  // public HistoryData getData(){
  //   return multiDataService.getData();
  // }
  
  @Override
  public List<HistoryData> getDataList(){
    return multiDataService.getDataList();
  }
}
