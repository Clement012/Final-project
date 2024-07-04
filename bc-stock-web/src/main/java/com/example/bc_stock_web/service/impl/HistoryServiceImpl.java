package com.example.bc_stock_web.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.bc_stock_web.service.HistoryService;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.mapper.HistoryDataEntityMapper;
import com.example.bc_stock_web.model.HistoryData;
import com.example.bc_stock_web.model.HistoryData.Chart;
import com.example.bc_stock_web.model.HistoryData.Result;
import com.example.bc_stock_web.repository.HistoryDataRepository;

@Service
public class HistoryServiceImpl implements HistoryService{

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HistoryDataRepository historyDataRepository;

  @Autowired
  private HistoryDataEntityMapper historyDataEntityMapper;

  @Override
  public HistoryData getHistoryData() {  
    String url = "https://query1.finance.yahoo.com/v8/finance/chart/0388.HK?period1=1688140800&period2=1719736200&interval=1d&events=history";
    HistoryData data = restTemplate.getForObject(url, HistoryData.class);  //1719763199  1688140800
    return data;
  }

  @Override
  public void saveData(){
      getHistoryData().getChart().getResult().stream().
            map(r -> historyDataEntityMapper.map(r))
            .forEach(r -> historyDataRepository.saveAll(r));
      //  hd.map(h -> historyDataEntityMapper.map(h))
      //  .forEach(h -> historyDataRepository.save(h));
  }

}
