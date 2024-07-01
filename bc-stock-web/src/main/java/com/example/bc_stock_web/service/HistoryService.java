package com.example.bc_stock_web.service;

import java.util.List;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.model.HistoryData;

public interface HistoryService {

  HistoryData getHistoryData();

  void saveData();
}
