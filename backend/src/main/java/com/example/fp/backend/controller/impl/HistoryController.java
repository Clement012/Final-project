package com.example.fp.backend.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.RestController;
import com.example.fp.backend.controller.HistoryOperation;
import com.example.fp.backend.dto.CandleStickDTO;
import com.example.fp.backend.entity.HistoryDataEntity;
import com.example.fp.backend.mapper.CandleStickMapper;
import com.example.fp.backend.model.HistoryData;
import com.example.fp.backend.repository.HistoryDataRepository;
import com.example.fp.backend.service.HistoryService;

@RestController
public class HistoryController implements HistoryOperation{
  
  @Autowired
  private HistoryDataRepository historyDataRepository;

  @Autowired
  private CandleStickMapper candleStickMapper;

  @Autowired
  private HistoryService historyService;

  @Override
  public List<CandleStickDTO> getPrice(){
    return historyDataRepository.findAll().stream()
        .map(h -> candleStickMapper.map(h))
        .collect(Collectors.toList());
  }
  
  @Override
  public List<HistoryData> getDataList(){
    return historyService.getDataList();
  }

  @Override
  public List<HistoryDataEntity> getHistoryEntity(String symbol)throws NotFoundException{
    return historyService.getHistoryEntity(symbol);
  }
}
