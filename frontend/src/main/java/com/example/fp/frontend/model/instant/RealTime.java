package com.example.fp.frontend.model.instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealTime {
  private Long id;
  private String time;
  private double regularMarketPrice;
  private double averagePrice;
  private String type;
  private String date;
  private Long timestamp;
  private String symbol;
}

