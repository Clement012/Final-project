package com.example.sb.bc_yahoo_finance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntityDTO {

  private String symbol;
  private String regularMarketUnix; 
  private double regularMarketPrice;
  private double regularMarketChangePercent;
  private double bid;
  private double ask;
  private int bidSize;
  private int askSize;
}