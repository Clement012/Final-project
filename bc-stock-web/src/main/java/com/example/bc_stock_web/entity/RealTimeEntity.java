package com.example.bc_stock_web.entity;

import java.time.LocalDateTime;
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
@Table(name = "storeperfivemin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealTimeEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime time;
  private double regularMarketPrice;
  private double averagePrice;
  private String type;
  private LocalDate date;
  private Long timestamp;
  private String symbol;
  private double bid;
  private double ask;

}
