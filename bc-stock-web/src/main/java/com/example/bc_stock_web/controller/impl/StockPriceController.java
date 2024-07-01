package com.example.bc_stock_web.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bc_stock_web.controller.StockPriceOperation;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.entity.StockDataEntity;
import com.example.bc_stock_web.mapper.StockBidAskMapper;
import com.example.bc_stock_web.mapper.StockMapper;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import com.example.bc_stock_web.model.StockPrice;
import com.example.bc_stock_web.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@RestController
public class StockPriceController implements StockPriceOperation {

//   private final List<StockDataOutput> dataFive = new ArrayList<>();

  @Autowired
  private StockService stockService;

  @Autowired
  private StockMapper stockMapper;

  @Autowired
  private StockBidAskMapper stockBidAskMapper;

  @Override
  public List<StockDTO> getStockData(){
    // return stockService.getStockData();
    List<Stock> datas = stockService.getStockData();
    List<StockDTO> dto = new ArrayList<>();
    for (Stock data : datas) {
        QuoteResponse quoteResponse = data.getQuoteResponse();
        if (quoteResponse != null) {
            Result[] results = quoteResponse.getResult();
            if (results != null) {
                List<StockDTO> stock = Arrays.stream(results)
                    .map(r -> stockMapper.map(r)) 
                    .collect(Collectors.toList());
                dto.addAll(stock);
            }
        }
    }
    return dto;
 }

 @Override
 public List<StockDataEntity> getPrice(){
    return stockService.getPrice();
 }

 @Override
 public List<StockBidAskDTO> getBidAsks(){
  List<Stock> datas = stockService.getStockData();
  List<StockBidAskDTO> dto = new ArrayList<>();
  for (Stock data : datas) {
      QuoteResponse quoteResponse = data.getQuoteResponse();
      if (quoteResponse != null) {
          Result[] results = quoteResponse.getResult();
          if (results != null) {
              List<StockBidAskDTO> stock = Arrays.stream(results)
                  .map(r -> stockBidAskMapper.map(r)) 
                  .collect(Collectors.toList());
              dto.addAll(stock);
          }
      }
  }
  return dto;
 }


 @Override
 public List<StockDataEntity> getFive(){
    return stockService.calculateMA();
 }
}
//  @Override
//  public void savePrice(){
//     stockService.savePrice();
//  }
  
//   @Override
//   public List<StockPrice> getFiveMinuteData() {
//     return Arrays.asList( //
//         new StockPrice(2024, 5, 27, 9, 0, 150.0), //
//         new StockPrice(2024, 5, 27, 9, 5, 151.0), //
//         new StockPrice(2024, 5, 27, 9, 10, 152.0), //
//         new StockPrice(2024, 5, 27, 9, 15, 153.0), //
//         new StockPrice(2024, 5, 27, 9, 20, 154.0), //
//         new StockPrice(2024, 5, 27, 9, 25, 155.0), //
//         new StockPrice(2024, 5, 27, 9, 30, 156.0), //
//         new StockPrice(2024, 5, 27, 9, 35, 157.0), //
//         new StockPrice(2024, 5, 27, 9, 40, 158.0), //
//         new StockPrice(2024, 5, 27, 9, 45, 159.0), //
//         new StockPrice(2024, 5, 27, 9, 50, 159.0), //
//         new StockPrice(2024, 5, 27, 9, 55, 159.0), //
//         new StockPrice(2024, 5, 27, 10, 0, 154.0), //
//         new StockPrice(2024, 5, 27, 10, 5, 158.0), //
//         new StockPrice(2024, 5, 27, 10, 10, 160.0), //
//         new StockPrice(2024, 5, 27, 10, 15, 170.0), //
//         new StockPrice(2024, 5, 27, 10, 20, 159.0), //
//         new StockPrice(2024, 5, 27, 10, 25, 158.0), //
//         new StockPrice(2024, 5, 27, 10, 30, 143.0), //
//         new StockPrice(2024, 5, 27, 10, 35, 160.0), //
//         new StockPrice(2024, 5, 27, 10, 40, 190.0), //
//         new StockPrice(2024, 5, 27, 10, 45, 149.0), //
//         new StockPrice(2024, 5, 27, 10, 50, 170.0), //
//         new StockPrice(2024, 5, 27, 10, 55, 168.0), //
//         new StockPrice(2024, 5, 27, 11, 0, 159.0) //
//     );
//   }
//  @Override
//  public List<StockDataOutput> getPrice(){  //get data from db
//     List<Stock> datas = stockService.getStockData();
//     List<StockDataOutput> dto = new ArrayList<>();
//     for (Stock data : datas) {
//         QuoteResponse quoteResponse = data.getQuoteResponse();
//         if (quoteResponse != null) {
//             Result[] results = quoteResponse.getResult();
//             if (results != null) {
//                 List<StockDataOutput> stock = Arrays.stream(results)
//                     .map(r -> outputMapper.map(r)) 
//                     .collect(Collectors.toList());
//                 dto.addAll(stock);
//                 dataFive.addAll(stock);
//             }
//         }
//     }
//     return dataFive;
//  }
 