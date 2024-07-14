package com.example.fp.frontend.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.fp.frontend.model.history.CandleStick;
import com.example.fp.frontend.model.history.HistoryData;
import com.example.fp.frontend.model.history.HistoryEntity;
import com.example.fp.frontend.model.instant.BidAsk;
import com.example.fp.frontend.model.instant.InstantData;
import com.example.fp.frontend.model.instant.Stock;
import com.example.fp.frontend.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{

  @Value("${api.localhosthistory.domain}")
  private String domain;

  @Value("${api.localhosthistory.path.candle}")
  private String candlePath;

  @Value("${api.localhosthistory.path.data}")
  private String dataPath;

  @Value("${api.localhosthistory.path.entity}")
  private String entityPath;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CandleStick> getPrice(){
    String url = "http://" + domain + "/" + candlePath;
    CandleStick[] candles = restTemplate.getForObject(url, CandleStick[].class);
    return Arrays.asList(candles);
  }

  @Override
  public List<HistoryData> getDataList(){
    String url = "http://" + domain + "/" + dataPath;
    HistoryData[] historyDatas = restTemplate.getForObject(url, HistoryData[].class);
    return Arrays.asList(historyDatas);
  }

  @Override
  public List<HistoryEntity> getHistoryEntity(String symbol){
    String url = "http://" + domain + "/" + entityPath + "/" + symbol;
    HistoryEntity[] entities = restTemplate.getForObject(url, HistoryEntity[].class);
    return Arrays.asList(entities);
  }
}
