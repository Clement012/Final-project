package com.example.fp.backend.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryData {

  private Chart chart;
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Chart {
      private List<Result> result;
      private Object error;  
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Result {
      private Meta meta;
      private List<Long> timestamp;
      private Indicators indicators;
      }
  

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Meta {
      private String currency;
      private String symbol;
      private String exchangeName;
      private String fullExchangeName;
      private String instrumentType;
      private long firstTradeDate;
      private long regularMarketTime;
      private boolean hasPrePostMarketData;
      private int gmtoffset;
      private String timezone;
      private String exchangeTimezoneName;
      private double regularMarketPrice;
      private double fiftyTwoWeekHigh;
      private double fiftyTwoWeekLow;
      private double regularMarketDayHigh;
      private double regularMarketDayLow;
      private long regularMarketVolume;
      private double chartPreviousClose;
      private int priceHint;
      private CurrentTradingPeriod currentTradingPeriod;
      private String dataGranularity;
      private String range;
      private List<String> validRanges;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CurrentTradingPeriod {
      private TradingPeriod pre;
      private TradingPeriod regular;
      private TradingPeriod post;

  }
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class TradingPeriod {
      private String timezone;
      private long start;
      private long end;
      private int gmtoffset;

  }
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Indicators {
      private List<Quote> quote;
      private List<AdjClose> adjclose;

  }
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Quote {
      private List<Double> open;
      private List<Double> close;
      private List<Long> volume;
      private List<Double> low;
      private List<Double> high;


  }
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AdjClose {
      private List<Double> adjclose;
  }
}
