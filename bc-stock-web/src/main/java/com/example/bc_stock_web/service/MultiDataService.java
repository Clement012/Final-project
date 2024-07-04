package com.example.bc_stock_web.service;

import java.util.List;
import com.example.bc_stock_web.model.HistoryData;

public interface MultiDataService {
  
  // HistoryData getData();

  void saveData();
  
  void clearData();

  List<HistoryData> getDataList();

}
