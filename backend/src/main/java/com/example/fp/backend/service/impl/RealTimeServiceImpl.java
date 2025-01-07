package com.example.fp.backend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.fp.backend.config.YahooConnector;
import com.example.fp.backend.entity.RealTimeEntity;
import com.example.fp.backend.mapper.RealTimeEntityMapper;
import com.example.fp.backend.model.InstantData;
import com.example.fp.backend.repository.RealTimeRepository;
import com.example.fp.backend.service.RealTimeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RealTimeServiceImpl implements RealTimeService {

  private final static Logger log = LoggerFactory.getLogger(RealTimeService.class);
  
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private RealTimeEntityMapper mapper;

  @Autowired
  private RealTimeRepository repository;

  @Value("${api.yahoo-finance.domain}")
  private String domain;

  @Value("${api.yahoo-finance.endpoint}")
  private String[] endpoints;

  @Value("${api.yahoo-finance.crumb}")
  private String crumbWord;

  //https://query1.finance.yahoo.com/v1/test/getcrumb 
  @Override
  public List<InstantData> getInstant(){
    List<InstantData> instantdatas = new ArrayList<>();
    String crumb = YahooConnector.getCrumb();
    String cookie = YahooConnector.getCookie();
    for (String endpoint : endpoints) {
      String url = String.format("https://%s%s%s%s", domain, endpoint, crumbWord, crumb);
      System.out.println("url " + url);
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");          
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = responseEntity.getBody();
        InstantData instantData = objectMapper.readValue(responseBody, InstantData.class); 
        instantdatas.add(instantData);
    } catch (Exception e) {
        log.error("Failed to get response from Yahoo Finance", e);
        return Collections.emptyList(); 
     }
   }
   return instantdatas;
  }

  @Override
  public void saveInstant(){
    List<InstantData> instantdatas = getInstant();
     for (InstantData instant : instantdatas){
       Arrays.stream(instant.getQuoteResponse().getResult())
         .map(r -> mapper.map(r))
         .forEach(r -> repository.save(r));
     }
  }
  @Override
  public List<RealTimeEntity> getRealTime(String symbol) throws NotFoundException {
    Optional<List<RealTimeEntity>> entities = repository.findBySymbol(symbol);
    List<RealTimeEntity> output = new ArrayList<>();
     if (entities.isPresent()){
      List<RealTimeEntity> entityList = entities.get();
      for (RealTimeEntity entity : entityList){
        if (entity.getType().equals("5-MINS")){
          output.add(entity);
        }
      }
      return output;
     }
     throw new NotFoundException();
    //  return repository.findAll();
  }
  
  @Override
  public List<RealTimeEntity> calculateMA(String symbol) throws NotFoundException{
    Optional<List<RealTimeEntity>> entities = repository.findBySymbol(symbol);
    if (entities.isPresent()){
      List<RealTimeEntity> output = new ArrayList<>();
      List<RealTimeEntity> entityList = entities.get();
      Double sum = 0.0;
      for (int i = 0; i < entityList.size(); i++) {
        RealTimeEntity entity = entityList.get(i);
        sum += entity.getRegularMarketPrice();
        double averagePrice = sum / (i + 1);
        entity.setAveragePrice(averagePrice);
        output.add(entity);
        }
        return output;
    }
    throw new NotFoundException();
  }
  @Override
  public void clear(){
    List<RealTimeEntity> entities = repository.findAll();
    if (entities.size() > 0){
      repository.deleteAll();
    }
  }
}
