package com.example.bc_stock_web.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.entity.RealTimeEntity;
import com.example.bc_stock_web.mapper.BidAskEntityMapper;
import com.example.bc_stock_web.mapper.RealTimeEntityMapper;
import com.example.bc_stock_web.mapper.StockMapper;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import com.example.bc_stock_web.repository.RealTimeRepository;
import com.example.bc_stock_web.service.RealTimeService;

@Service
public class RealTimeServiceImpl implements RealTimeService{
  
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RealTimeRepository repository;

  @Autowired
  private RealTimeEntityMapper mapper;

  @Autowired
  private BidAskEntityMapper bamapper;

  @Override   
  public List<Result> getResults(){  
    String url = "http://localhost:8096/real";
    System.out.println("url=" + url);
    Stock[]stockdatas = restTemplate.getForObject(url, Stock[].class);
    List<Result> results = new ArrayList<>();
    if (stockdatas != null){
    for (Stock data : stockdatas){
      QuoteResponse quoteResponse = data.getQuoteResponse();
      if (quoteResponse != null) {
          Result[] result = quoteResponse.getResult();
          results.addAll(Arrays.asList(result));
          }
    }
  }
    return results;
  }

  @Override
  public List<Stock> getStockData(){  //0388
  String url = "http://localhost:8096/stock/hke";
  Stock []stockdata = restTemplate.getForObject(url, Stock[].class);
  return Arrays.asList(stockdata);
  }

  @Override
  public List<Stock> getDataList(){ //all
    String url = "http://localhost:8096/real";
    Stock []stockdata = restTemplate.getForObject(url, Stock[].class);
    return Arrays.asList(stockdata);
  }

  @Override
  public void savePrice(){
      List<Stock> datas = getDataList();
    for (Stock data : datas) {
        QuoteResponse quoteResponse = data.getQuoteResponse();
        if (quoteResponse != null) {
            Result[] results = quoteResponse.getResult();
            if (results != null) {
                     Arrays.stream(results)
                    .map(r -> mapper.map(r)) 
                    .forEach(r ->repository.save(r));
        }
      }
    }
  }

  @Override
  public List<RealTimeEntity> getRealTime(String symbol) throws NotFoundException {
    Optional<List<RealTimeEntity>> entities = repository.findBySymbol(symbol);
    List<RealTimeEntity> output = new ArrayList<>();
     if (entities.isPresent()){
      List<RealTimeEntity> entityList = entities.get();
      for (RealTimeEntity entity : entityList){
        if (entity.getType().equals("5-MINS")){
          output.add(entity);
        }
      }
      return output;
     }
     throw new NotFoundException();
    //  return repository.findAll();
  }
  
  @Override
  public List<RealTimeEntity> calculateMA(String symbol) throws NotFoundException{
    Optional<List<RealTimeEntity>> entities = repository.findBySymbol(symbol);
    if (entities.isPresent()){
      List<RealTimeEntity> output = new ArrayList<>();
      List<RealTimeEntity> entityList = entities.get();
      Double sum = 0.0;
      for (int i = 0; i < entityList.size(); i++) {
        RealTimeEntity entity = entityList.get(i);
        sum += entity.getRegularMarketPrice();
        double averagePrice = sum / (i + 1);
        entity.setAveragePrice(averagePrice);
        output.add(entity);
        }
        return output;
    }
    throw new NotFoundException();
}


  @Override
  public void clearData(){
    List<RealTimeEntity> entities = repository.findAll();
    if (entities.size() > 0){
      repository.deleteAll();
    }
  }

  @Override
  public List<RealTimeEntity> getBidAskEntity(String symbol) throws NotFoundException{
    Optional<List<RealTimeEntity>> entities = repository.findBySymbol(symbol);
    List<RealTimeEntity> output = new ArrayList<>();
     if (entities.isPresent()){
      List<RealTimeEntity> entityList = entities.get();
      RealTimeEntity latestEntity = null;
      Long largestTimestamp = 0l;
      for (RealTimeEntity entity : entityList){
        if (entity.getType().equals("Instant")){  //logic: add one entity with largest timestamp
            if (entity.getTimestamp() > largestTimestamp){
              largestTimestamp = entity.getTimestamp();
              latestEntity = entity;
            }
        }
      }
      if (latestEntity != null) {
        output.add(latestEntity);
     }
      return output;
     }
     throw new NotFoundException();
  }


  @Override
  public void saveBidAsk(){
      List<Stock> datas = getDataList();
    for (Stock data : datas) {
        QuoteResponse quoteResponse = data.getQuoteResponse();
        if (quoteResponse != null) {
            Result[] results = quoteResponse.getResult();
            if (results != null) {
                     Arrays.stream(results)
                    .map(r -> bamapper.map(r)) 
                    .forEach(r ->repository.save(r));
        }
      }
    }
  }
}

// @Override
// public List<RealTimeEntity> calculateMA(String symbol) throws NotFoundException{  // if there's more then one set data //with diifferent date
//   List<RealTimeEntity> entities = repository.findAll(); // all field of entity
//   List<RealTimeEntity> output = new ArrayList<>();

//   double totalPrice = 0.0;
//   for (RealTimeEntity entity : entities){
//     if (entity.getDate().isEqual(LocalDate.now())) {
//     Double price = entity.getRegularMarketPrice();  
//     totalPrice += price;
//     entity.setAveragePrice(totalPrice/entity.getId());  //have to divide data number not id
//     output.add(entity);
//     }
//   }
//   return output;
    
//   }
////#0388


  // @Override
  // public List<RealTimeEntity> getPrice(){
  //   return repository.findAll();
  // }

  // @Override
  // public void savePrice(){
  //     List<Stock> datas = getStockData();
  //   for (Stock data : datas) {
  //       QuoteResponse quoteResponse = data.getQuoteResponse();
  //       if (quoteResponse != null) {
  //           Result[] results = quoteResponse.getResult();
  //           if (results != null) {
  //                    Arrays.stream(results)
  //                   .map(r -> mapper.map(r)) 
  //                   .forEach(r ->repository.save(r));
  //           }
  //       }
  //    }
  //  }


  // @Override
  // public List<Stock> getStockData(){
  //   List<Stock> datas = getStockData();
  //   List<StockDTO> dto = new ArrayList<>();
  //   for (Stock data : datas) {
  //       QuoteResponse quoteResponse = data.getQuoteResponse();
  //       if (quoteResponse != null) {
  //           Result[] results = quoteResponse.getResult();
  //           if (results != null) {
  //               List<StockDTO> stock = Arrays.stream(results)
  //                   .map(r -> stockMapper.map(r)) 
  //                   .collect(Collectors.toList());
  //               dto.addAll(stock);
  //           }
  //       }
  //   }
  //   return dto;
  // }

  // @Override
  // public void savePrice(){
  //   List<Stock> stocks = getStockData();
  //     // List<ResultEntityDTO> dtoList = new ArrayList<>();
  //   for (Stock stock : stocks) {
  //       QuoteResponse quoteResponse = stock.getQuoteResponse();
  //       if (quoteResponse != null) {
  //           Result[] results = quoteResponse.getResult();
  //           if (results != null) {
  //                    Arrays.stream(results)
  //                   .map(r -> stockDataEntityMapper.map(r)) 
  //                   .forEach(r -> sdRepository.save(r));
  //               // stockDataRepository.saveAllAndFlush(dto);  //map as Entity.
  //           }
  //       }
  //    }
  // }
  // public List<StockDataEntity> calculateMA(){  // if there's more then one set data //with diifferent date
  // List<StockDataEntity> entities = sdRepository.findAll(); // all field of entity
  //  List<StockDataEntity> output = new ArrayList<>();

 //   Map<LocalDate, List<StockDataEntity>> entitiesByDate = entities.stream()
 //       .collect(Collectors.groupingBy(StockDataEntity::getDate));

 //    for (Map.Entry<LocalDate, List<StockDataEntity>> entry : entitiesByDate.entrySet()) {
 //         LocalDate date = entry.getKey();
 //         List<StockDataEntity> dataForDate = entry.getValue();
 
 //         double totalPrice = 0.0;
 //         int numEntities = 0;
 //         for (StockDataEntity entity : dataForDate) {
 //             totalPrice += entity.getRegularMarketPrice();
 //             numEntities++;
 //         }
 
 //         double averagePrice = totalPrice / numEntities;
 
 //         for (StockDataEntity entity : dataForDate) {
 //             entity.setAveragePrice(averagePrice);
 //             output.add(entity);
 //         }
 //     }

 //   return output;
 // }
   // List<StockDataEntity> entities = sdRepository.findAll(); // all field of entity
   // List<StockDataEntity> output = new ArrayList<>();
   // // if (entities.size() == 0){
   // //   throw new NullPointerException("Non-trading period");
   // // }
   //double totalPrice = 0.0;
   // for (int i = 0; i < entities.size(); i++){
   //   StockDataEntity entity = entities.get(i);
   //   if (entity.getDate().isEqual(LocalDate.now())) {
   //         totalPrice += entity.getRegularMarketPrice();
   //         entity.setAveragePrice(totalPrice / entities.size());
   //         output.add(entity);
   //     }
   // }
   // return output;
   //   }

