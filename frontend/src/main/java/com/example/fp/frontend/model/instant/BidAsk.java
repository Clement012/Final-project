package com.example.fp.frontend.model.instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidAsk {
  private String symbol;
  private double bid;
  private double ask;
}
