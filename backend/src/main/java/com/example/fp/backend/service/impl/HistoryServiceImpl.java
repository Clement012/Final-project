package com.example.fp.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.fp.backend.entity.HistoryDataEntity;
import com.example.fp.backend.mapper.HistoryDataEntityMapper;
import com.example.fp.backend.model.HistoryData;
import com.example.fp.backend.repository.HistoryDataRepository;
import com.example.fp.backend.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {
  
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HistoryDataEntityMapper mapper;

  @Autowired
  private HistoryDataRepository historyDataRepository;

  @Value("${api.yahoo-finance-history.domain}")
  private String domain;

  @Value("${api.yahoo-finance-history.history.base-path}")
  private String basePath;
  
  @Value("${api.yahoo-finance-history.history.endpoint}")
  private String[] endpoints;

 @Override
  public void saveData(){
    List<HistoryData> hds = getDataList();
      for (HistoryData hd : hds){
            hd.getChart().getResult().stream().
            map(r -> mapper.map(r))
            .forEach(r -> historyDataRepository.saveAll(r));
      }
  }

  @Override
  public void clearData(){
    List<HistoryDataEntity> historyDatas = historyDataRepository.findAll();
    if (historyDatas.size() > 0){
      historyDataRepository.deleteAll();
    }
  }

  @Override
    public List<HistoryData> getDataList(){
    List<HistoryData> hd = new ArrayList<>();
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("period1", "1577808000"); 
        queryParams.add("period2", "1719763200");
        queryParams.add("interval", "1d");
        queryParams.add("events", "history");
    System.out.println("queryParams=" + queryParams);
    for (String endpoint : endpoints){
    String url = UriComponentsBuilder.newInstance()
         .scheme("https")
         .host(domain)
         .path(basePath)
         .path(endpoint)
         .queryParams(queryParams)
         .toUriString();
     try {
      HistoryData historyData = restTemplate.getForObject(url, HistoryData.class);
      System.out.println("history=" + historyData);
      hd.add(historyData);
      } catch (Exception e) {
      System.err.println("Failed to fetch data from " + url + ": " + e.getMessage());
      }
     }
     return hd;
    }

   @Override
    public List<HistoryDataEntity> getHistoryEntity(String symbol) throws NotFoundException{
      Optional<List<HistoryDataEntity>> entities = historyDataRepository.findBySymbol(symbol);
      if (entities.isPresent()){
        return entities.get();
      }
      throw new NotFoundException();
    }
}
