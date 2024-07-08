package com.example.bc_stock_web.service;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.example.bc_stock_web.entity.RealTimeEntity;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;

public interface RealTimeService {

  List<Result> getResults();  

  List<Stock> getDataList(); 

  void savePrice();

  List<RealTimeEntity> getRealTime(String symbol) throws NotFoundException;

  List<RealTimeEntity> calculateMA(String symbol) throws NotFoundException;

  List<RealTimeEntity> getBidAskEntity(String symbol) throws NotFoundException;

  void clearData();  

  List<Stock> getStockData(); //0388

  void saveBidAsk();

}
