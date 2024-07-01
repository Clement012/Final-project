package com.example.sb.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TSTOCK_QUOTE_YAHOO")
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private String regularMarketUnix; 
  private double regularMarketPrice;
  private double regularMarketChangePercent;
  private double bid;
  private double ask;
  private int bidSize;
  private int askSize;
  private LocalDateTime apiDateTime;
  private LocalDate systemDate;
}
