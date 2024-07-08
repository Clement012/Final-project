package com.example.bc_stock_web.service;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.model.HistoryData;

public interface HistoryDataService {
  
  // HistoryData getData();

  void saveData();
  
  void clearData();

  List<HistoryData> getDataList();

  List<HistoryDataEntity> getHistoryEntity(String symbol)throws NotFoundException;

}
