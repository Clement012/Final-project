package com.example.fp.frontend.model.instant;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
  private String symbol;
  private String regularMarketUnix; 
  private double regularMarketPrice;
  private double regularMarketChange;
  private double regularMarketChangePercent;
  private double bid;
  private double ask;
  private String timeNow; //
  private String longName;
  private long regularMarketVolume;
  private double marketCap;
}
