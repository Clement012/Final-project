package com.example.bc_stock_web.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tstock_quote_yahoo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDataEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private String type;
  private Long timestamp; 
  private LocalDate date;
  private double open;  // inside indicators
  private double close;
  private double low;
  private double high;
  private double adjclose;
  private Long volume;
  // private double regularMarketPrice;
  
}
