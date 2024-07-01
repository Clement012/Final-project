package com.example.bc_stock_web.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.bc_stock_web.controller.HistoryOperation;
import com.example.bc_stock_web.service.HistoryService;
import com.example.bc_stock_web.model.HistoryData;

@RestController
public class HistoryController implements HistoryOperation {
  
  @Autowired
  private HistoryService historyService;

  @Override
  public HistoryData getHistoryData(){
    return historyService.getHistoryData();
  }
}
