package com.example.fp.frontend.service;

import java.util.List;
import com.example.fp.frontend.model.instant.BidAsk;
import com.example.fp.frontend.model.instant.InstantData;
import com.example.fp.frontend.model.instant.RealTime;
import com.example.fp.frontend.model.instant.Stock;

public interface RealTimeService {

  List<Stock> getStocks();

  List<InstantData> getInstants();

  List<BidAsk> getBidAsks(String symbol);

  List<RealTime> getPrice(String symbol);

  List<RealTime> getFive(String symbol);
}
