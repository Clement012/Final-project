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
public class HistoryEntity {
  private Long id;
  private String symbol;
  private String type;
  private Long timestamp; 
  private String date;
  private double open;  // inside indicators
  private double close;
  private double low;
  private double high;
  private double adjclose;
  private Long volume;
}
