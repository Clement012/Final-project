package com.example.bc_stock_web.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class StockPrice {
  private String timestamp;
  private double price;

  public StockPrice(int year, int month, int day, int hour, int minute,
      double price) {
    this.timestamp = LocalDateTime.of(year, month, day, hour, minute, 0) //
        .atZone(ZoneId.of("Asia/Hong_Kong")) //
        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    System.out.println(timestamp);
    this.price = price;
  }

  public StockPrice(LocalDateTime timestamp, double price) {
    this.timestamp = timestamp.atZone(ZoneId.of("Asia/Hong_Kong"))
        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    this.price = price;
  }
}
