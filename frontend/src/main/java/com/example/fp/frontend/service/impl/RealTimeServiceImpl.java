package com.example.fp.frontend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.fp.frontend.model.instant.BidAsk;
import com.example.fp.frontend.model.instant.InstantData;
import com.example.fp.frontend.model.instant.RealTime;
import com.example.fp.frontend.model.instant.Stock;
import com.example.fp.frontend.service.RealTimeService;

@Service
public class RealTimeServiceImpl implements RealTimeService{

  @Value("${api.localhostinstant.domain}")
  private String domain;

  @Value("${api.localhostinstant.path.info}")
  private String infoPath;

  @Value("${api.localhostinstant.path.instant}")
  private String instantPath;

  @Value("${api.localhostinstant.path.bidask}")
  private String bidaskPath;

  @Value("${api.localhostinstant.path.fivemin}")
  private String fiveminPath;

  @Value("${api.localhostinstant.path.ma}")
  private String maPath;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<Stock> getStocks() {
    String url = "http://" + domain + "/" + infoPath;
    Stock[] stocks = restTemplate.getForObject(url, Stock[].class);
    return Arrays.asList(stocks);
  }

  @Override
  public List<InstantData> getInstants() {
    String url = "http://" + domain + "/" + instantPath;
    InstantData[] instantDatas = restTemplate.getForObject(url, InstantData[].class);
    return Arrays.asList(instantDatas);
  }

  @Override
  public List<BidAsk> getBidAsks(String symbol) {
    String url = "http://" + domain + "/" + bidaskPath + "/" + symbol;
    BidAsk[] bidAsks = restTemplate.getForObject(url, BidAsk[].class);
    // List<BidAsk> output = new ArrayList<>();
    //  if (bidAsks != null){
    //   for (BidAsk bidask : bidAsks){
    //       if (bidask.getSymbol() == symbol) {
    //         output.add(bidask);
    //       }
    //   }
    // }
    return Arrays.asList(bidAsks);
  }

  @Override
  public List<RealTime> getPrice(String symbol) {
    String url = "http://" + domain + "/" + fiveminPath + "/" + symbol;
    RealTime[] realTimes = restTemplate.getForObject(url, RealTime[].class);
    return Arrays.asList(realTimes);
  }

  @Override
  public List<RealTime> getFive(String symbol) {
    String url = "http://" + domain + "/" + maPath + "/" + symbol;
    RealTime[] realTimes = restTemplate.getForObject(url, RealTime[].class);
    return Arrays.asList(realTimes);
  }
}
