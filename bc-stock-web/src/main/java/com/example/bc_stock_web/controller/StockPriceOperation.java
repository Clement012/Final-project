package com.example.bc_stock_web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bc_stock_web.model.StockPrice;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.entity.StockDataEntity;
import com.example.bc_stock_web.model.Stock;

public interface StockPriceOperation {
   // @GetMapping(value = "/five-minute")
   // List<StockPrice> getFiveMinuteData();

   @GetMapping(value = "/test")
   List<StockDTO> getStockData();

   @GetMapping(value = "/five-minute")
   List<StockDataEntity> getPrice();

   @GetMapping(value = "/ma")
   List<StockDataEntity> getFive();

   @GetMapping(value = "/bidask")
   List<StockBidAskDTO> getBidAsks();
}
