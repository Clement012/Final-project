package com.example.sb.bc_yahoo_finance.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.sb.bc_yahoo_finance.infra.GlobalConstants;
import com.example.sb.bc_yahoo_finance.model.Response;
import com.example.sb.bc_yahoo_finance.service.RealTimeDataService;
import com.example.sb.bc_yahoo_finance.service.StockDataServiceHKE;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RealTimeDataImpl implements RealTimeDataService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.yahoo-finance.domain}")
  private String domain;

  @Value("${api.yahoo-finance.endpoint}")
  private String[] endpoints;

  @Value("${api.yahoo-finance.crumb}")
  private String crumbWord;

  private final static Logger log = LoggerFactory.getLogger(RealTimeDataService.class);

  public static String crumb = null; 
  public static String cookie = null; 

  private static void setCookie() {
    try {
      URL url = new URI("https://fc.yahoo.com").toURL();
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      // Get the cookie from the response headers
      cookie = connection.getHeaderField("Set-Cookie");
      connection.disconnect();
    } catch (Exception e) {
      log.debug("Failed to set cookie from http request. Intraday quote requests will most likely fail.", e);
    }
  }

  private static void setCrumb() {
    StringBuilder response = new StringBuilder();

    try {
      URL url = new URI("https://query1.finance.yahoo.com/v1/test/getcrumb").toURL(); 
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      // Set the cookie
      connection.setRequestProperty("Cookie", cookie);
      connection.addRequestProperty("User-Agent", GlobalConstants.USER_AGENT);

      // Make the HTTP request
      connection.setRequestMethod("GET");

      // Read the response content
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          response.append(line);
        }
      }
    } catch (Exception e) {
      log.debug("Failed to set crumb from http request. Intraday quote requests will most likely fail.", e);
    }
    crumb = response.toString();
  }

  public static synchronized void resetCookieCrumb() {
    setCookie();
    setCrumb();
  }

  public static synchronized String getCookie() {
    if (cookie == null || cookie.isEmpty()) {
      resetCookieCrumb();
    }
    return cookie;
  }

  public static synchronized String getCrumb() {
    if (crumb == null || crumb.isBlank()) {
      resetCookieCrumb();
    }
    return crumb;
  }

  @Override
  public List<Response> getResponse() {
    List<Response> responses = new ArrayList<>();
    String crumb = getCrumb();
    System.out.println("Crumb="+ crumb);
    String cookie = getCookie();
    System.out.println("Cookie="+ cookie);
    
    for (String endpoint : endpoints) {
      // Construct URL
      String url = String.format("https://%s%s%s%s", domain, endpoint, crumbWord, crumb);
      System.out.println("url=" + url);
    try {
        // Create HttpHeaders object to set the Cookie and User-Agent headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("User-Agent", GlobalConstants.USER_AGENT);          
        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Log the request details
        // Make the request with headers and capture the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = responseEntity.getBody();
        // Extract the body from the response
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = objectMapper.readValue(responseBody, Response.class); 
        responses.add(response);
        // Return the response as a list
    } catch (Exception e) {
        log.error("Failed to get response from Yahoo Finance", e);
        return Collections.emptyList(); 
     }
   }
   return responses;
  }
}



