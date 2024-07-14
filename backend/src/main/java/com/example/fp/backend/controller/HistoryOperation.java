package com.example.fp.backend.controller;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.fp.backend.dto.CandleStickDTO;
import com.example.fp.backend.entity.HistoryDataEntity;
import com.example.fp.backend.model.HistoryData;

public interface HistoryOperation {
  
  @GetMapping (value = "/candle")
  List<CandleStickDTO> getPrice();

  @GetMapping (value = "/historydatalist")
  List<HistoryData> getDataList();

  @GetMapping (value = "/historyentity/{symbol}")
  List<HistoryDataEntity> getHistoryEntity(@PathVariable String symbol) throws NotFoundException;
}
