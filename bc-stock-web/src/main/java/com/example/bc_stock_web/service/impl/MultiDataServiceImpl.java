package com.example.bc_stock_web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.bc_stock_web.entity.HistoryDataEntity;
import com.example.bc_stock_web.infra.Scheme;
import com.example.bc_stock_web.infra.UrlBuilder;
import com.example.bc_stock_web.mapper.HistoryDataEntityMapper;
import com.example.bc_stock_web.model.HistoryData;
import com.example.bc_stock_web.repository.HistoryDataRepository;
import com.example.bc_stock_web.service.MultiDataService;

@Service
public class MultiDataServiceImpl implements MultiDataService{

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HistoryDataEntityMapper historyDataEntityMapper;

  @Autowired
  private HistoryDataRepository historyDataRepository;

  @Value("${api.yahoo-finance.domain}")
  private String domain;

  @Value("${api.yahoo-finance.history.base-path}")
  private String basePath;
  
  @Value("${api.yahoo-finance.history.endpoint}")
  private String[] endpoints;

  // api:
  // yahoo-finance:
  //   domain: 'query1.finance.yahoo.com'
  //   history:
  //     base-path: 'v8/finance/chart'
  //     endpoint: '/0388.HK'
  //  #  hke: '/0388.HK'
    //  #  hsbc: '/0005.HK'
    //  #  tcl: '/0700.HK'

//   @Override
//   public HistoryData getData(){
//     // String test = UrlBuilder.get(Scheme.HTTPS, domain, endpoint, basePath);
//     MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//         queryParams.add("period1", "1420041600"); // 2015-01-01
//         queryParams.add("period2", String.valueOf(System.currentTimeMillis()));  //now
//         queryParams.add("interval", "1d");
//         queryParams.add("events", "history");
//     String url = UriComponentsBuilder.newInstance()
//          .scheme("https")
//          .host(domain)
//          .path(basePath)
//          .path(endpoint)
//          .queryParams(queryParams)
//          .toUriString();
//      System.out.println("url=" + url);
//     HistoryData historyData = restTemplate.getForObject(url, HistoryData.class);
//     System.out.println("history" + historyData);
//     return historyData;
//     // return null;
//  }

 @Override
  public void saveData(){
    List<HistoryData> hds = getDataList();
      for (HistoryData hd : hds){
            hd.getChart().getResult().stream().
            map(r -> historyDataEntityMapper.map(r))
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
        queryParams.add("period1", "1420041600"); // 2015
        queryParams.add("period2", String.valueOf(System.currentTimeMillis()));  //now
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
     System.out.println("url=" + url);
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
}

