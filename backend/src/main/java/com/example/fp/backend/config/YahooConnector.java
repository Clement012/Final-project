package com.example.fp.backend.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.example.fp.backend.service.RealTimeService;

@Configuration
public class YahooConnector {

  private final static Logger log = LoggerFactory.getLogger(RealTimeService.class);

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
      connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");

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
}
