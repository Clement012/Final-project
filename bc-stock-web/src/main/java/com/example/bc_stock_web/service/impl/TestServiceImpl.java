package com.example.bc_stock_web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.bc_stock_web.model.HistoryData;

// @Service
public class TestServiceImpl {

//   @Autowired
//   private YahooFinanceProperties properties;

//   @Autowired
//   private RestTemplate restTemplate;

//   @Value("${api.yahoo-finance.domain}")
//   private String domain;

//   @Value("${api.yahoo-finance.history.base-path}")
//   private String basePath;
  
//   @Value("${api.yahoo-finance.history.endpoint}")
//   private List<String> endpoints;

  
//   public List<HistoryData> getDataList(){
//     List<HistoryData> hd = new ArrayList<>();
//     MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//         queryParams.add("period1", "1420041600"); // 2015
//         queryParams.add("period2", String.valueOf(System.currentTimeMillis()));  //now
//         queryParams.add("interval", "1d");
//         queryParams.add("events", "history");
//     for (String endpoint : properties.getEndpoints()){
//     String url = UriComponentsBuilder.newInstance()
//          .scheme("https")
//          .host(properties.getDomain())
//          .path(properties.getBasePath())
//          .path(endpoint)
//          .queryParams(queryParams)
//          .toUriString();
//      System.out.println("url=" + url);
//      try {
//       HistoryData historyData = restTemplate.getForObject(url, HistoryData.class);
//       System.out.println("history=" + historyData);
//       hd.add(historyData);
//   } catch (Exception e) {
//       System.err.println("Failed to fetch data from " + url + ": " + e.getMessage());
//       }
//      }
//      return hd;
//     }
}
