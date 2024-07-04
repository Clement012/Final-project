package com.example.bc_stock_web.dto;

import java.time.LocalDate;
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
public class CandleStickDTO {
  private Long timestamp; 
  private LocalDate date;
  private double open;  
  private double close;
  private double low;
  private double high;
  private Long volume;
}
