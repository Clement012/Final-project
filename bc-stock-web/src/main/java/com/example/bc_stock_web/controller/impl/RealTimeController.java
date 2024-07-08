package com.example.bc_stock_web.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.bc_stock_web.controller.RealTimeOperation;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.mapper.StockBidAskMapper;
import com.example.bc_stock_web.mapper.StockMapper;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import com.example.bc_stock_web.model.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.bc_stock_web.service.RealTimeService;
import com.example.bc_stock_web.entity.RealTimeEntity;

@RestController
public class RealTimeController implements RealTimeOperation {

//   private final List<StockDataOutput> dataFive = new ArrayList<>();

  @Autowired
  private RealTimeService realTimeService;

  @Autowired
  private StockMapper stockMapper;

  @Autowired
  private StockBidAskMapper stockBidAskMapper;

//   @Override
//   public List<Stock> test(){
//     return realTimeService.getDataList();
//   }

  @Override
  public List<StockDTO> getDataList(){
    // return realTimeService.getDataList();
    List<Stock> datas = realTimeService.getDataList();
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
 public List<StockBidAskDTO> getBidAsks(){
    return realTimeService.getResults().stream()
       .map(b -> stockBidAskMapper.map(b))
       .collect(Collectors.toList());
 }

// 0388
   @Override
 public List<StockBidAskDTO> getHKEBidAsks(){
    // return realTimeService.getStockData()
  List<Stock> datas = realTimeService.getStockData();
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
 public List<RealTimeEntity> getPrice(String symbol) throws NotFoundException{
    return realTimeService.getRealTime(symbol);
 }
 @Override
 public List<RealTimeEntity> getFive(String symbol) throws NotFoundException{
    return realTimeService.calculateMA(symbol);
}
@Override
public List<RealTimeEntity> getBidAskEntity(String symbol) throws NotFoundException{
    return realTimeService.getBidAskEntity(symbol);
}
 
}

// @Override
//   public List<StockDTO> getStockData(){
//     // return stockService.getStockData();
//     List<Stock> datas = realTimeService.getStockData();
//     List<StockDTO> dto = new ArrayList<>();
//     for (Stock data : datas) {
//         QuoteResponse quoteResponse = data.getQuoteResponse();
//         if (quoteResponse != null) {
//             Result[] results = quoteResponse.getResult();
//             if (results != null) {
//                 List<StockDTO> stock = Arrays.stream(results)
//                     .map(r -> stockMapper.map(r)) 
//                     .collect(Collectors.toList());
//                 dto.addAll(stock);
//             }
//         }
//     }
//     return dto;
//  }

//   @Override
//  public List<StockBidAskDTO> getBidAsks(){
//   List<Stock> datas = realTimeService.getDataList();
//   List<StockBidAskDTO> dto = new ArrayList<>();
//   for (Stock data : datas) {
//       QuoteResponse quoteResponse = data.getQuoteResponse();
//       if (quoteResponse != null) {
//           Result[] results = quoteResponse.getResult();
//           if (results != null) {
//               List<StockBidAskDTO> stock = Arrays.stream(results)
//                   .map(r -> stockBidAskMapper.map(r)) 
//                   .collect(Collectors.toList());
//               dto.addAll(stock);
//           }
//       }
//   }
//   return dto;
//  }

 
