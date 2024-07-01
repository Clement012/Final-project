package com.example.bc_stock_web.service;

import java.util.List;
import com.example.bc_stock_web.entity.StockDataEntity;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;

public interface StockService {

  List<Stock> getStockData();

  // List<Result> getResults();

  List<StockDataEntity> getPrice();

  List<StockDataEntity> calculateMA();

  void savePrice();

  void clearData();
}
