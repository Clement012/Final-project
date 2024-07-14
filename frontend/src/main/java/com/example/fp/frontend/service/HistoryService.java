package com.example.fp.frontend.service;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.fp.frontend.model.history.CandleStick;
import com.example.fp.frontend.model.history.HistoryData;
import com.example.fp.frontend.model.history.HistoryEntity;

public interface HistoryService {

  List<CandleStick> getPrice();

  List<HistoryData> getDataList();

  List<HistoryEntity> getHistoryEntity(String symbol);
}
