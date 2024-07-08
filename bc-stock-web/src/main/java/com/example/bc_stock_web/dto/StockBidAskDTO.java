package com.example.bc_stock_web.dto;

import java.time.LocalDateTime;
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
public class StockBidAskDTO {
  private String symbol;
  private double bid;
  private double ask;
  private LocalDateTime timeNow; 
  
}
