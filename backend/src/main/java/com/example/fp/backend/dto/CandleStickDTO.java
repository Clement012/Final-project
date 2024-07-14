package com.example.fp.backend.dto;

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
  private String date;
  private double open;  
  private double close;
  private double low;
  private double high;
  private Long volume;
}
