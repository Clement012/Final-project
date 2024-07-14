package com.example.fp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
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
