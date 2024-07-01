package com.example.bc_stock_web.dto;

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
  private double regularMarketChangePercent;
  private double bid;
  private double ask;
  private int bidSize;
  private int askSize;
  private LocalDateTime timeNow; //
}
