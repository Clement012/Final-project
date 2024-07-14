package com.example.fp.frontend.model.history;

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
public class CandleStick {
  private Long timestamp; 
  private String date;
  private double open;  
  private double close;
  private double low;
  private double high;
  private Long volume;
}
