package com.example.fp.backend.service;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.example.fp.backend.entity.RealTimeEntity;
import com.example.fp.backend.model.InstantData;

public interface RealTimeService {
  
  List<InstantData> getInstant();

  void saveInstant();

  List<RealTimeEntity> getRealTime(String symbol) throws NotFoundException;

  List<RealTimeEntity> calculateMA(String symbol) throws NotFoundException;

  void clear();

}
