package com.example.fp.frontend.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.fp.frontend.controller.HistoryOperation;
import com.example.fp.frontend.model.history.CandleStick;
import com.example.fp.frontend.model.history.HistoryData;
import com.example.fp.frontend.model.history.HistoryEntity;
import com.example.fp.frontend.service.HistoryService;

@RestController
public class HistoryController implements HistoryOperation {
  
  @Autowired
  private HistoryService historyService;

  @Override
  public List<CandleStick> getPrice(){
    return historyService.getPrice();
  }

  @Override
  public List<HistoryData> getDataList(){
    return historyService.getDataList();
  }

  @Override
  public List<HistoryEntity> getHistoryEntity(String symbol){
    return historyService.getHistoryEntity(symbol);
  }
}
