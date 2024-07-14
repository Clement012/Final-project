package com.example.fp.backend.service;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.example.fp.backend.entity.HistoryDataEntity;
import com.example.fp.backend.model.HistoryData;

public interface HistoryService {
  
  void saveData();
  
  void clearData();

  List<HistoryData> getDataList();

  List<HistoryDataEntity> getHistoryEntity(String symbol)throws NotFoundException;
}
