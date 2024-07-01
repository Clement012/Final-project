package com.example.bc_stock_web.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.entity.StockDataEntity;
import com.example.bc_stock_web.mapper.StockDataEntityMapper;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.model.Stock.QuoteResponse;
import com.example.bc_stock_web.model.Stock.QuoteResponse.Result;
import com.example.bc_stock_web.repository.SDRepository;
import com.example.bc_stock_web.service.StockService;

@Service
public class StockServiceImpl implements StockService{
  
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockDataEntityMapper stockDataEntityMapper;

  @Autowired
  private SDRepository sdRepository;

  @Override
  public List<Stock> getStockData(){ //all
  String url = "http://localhost:8096/stock/hke";
  Stock []stockdata = restTemplate.getForObject(url, Stock[].class);
  return Arrays.asList(stockdata);
  }

  // @Override
  // public List<Result> getResults(){  //only result
  //    List<Stock> stocks = getStockData();
  //    List<Result> results = new ArrayList<>();
  //    for (Stock data : stocks) {
  //     QuoteResponse quoteResponse = data.getQuoteResponse();
  //     if (quoteResponse != null) {
  //         Result[] result = quoteResponse.getResult();
  //         if (results != null) {
  //                  Arrays.stream(result)
  //                 .map(r -> stockDataEntityMapper.map(r)) 
  //         }
  //     }
  //  }
  // return Arrays.asList(results);
  // }

  @Override
  public List<StockDataEntity> getPrice(){
    return sdRepository.findAll();
  }

  @Override
  public void savePrice(){
      List<Stock> datas = getStockData();
    for (Stock data : datas) {
        QuoteResponse quoteResponse = data.getQuoteResponse();
        if (quoteResponse != null) {
            Result[] results = quoteResponse.getResult();
            if (results != null) {
                     Arrays.stream(results)
                    .map(r -> stockDataEntityMapper.map(r)) 
                    .forEach(r ->sdRepository.save(r));
            }
        }
     }
   }
  

  @Override
  public List<StockDataEntity> calculateMA(){  // if there's more then one set data //with diifferent date
   List<StockDataEntity> entities = sdRepository.findAll(); // all field of entity
    List<StockDataEntity> output = new ArrayList<>();

    double totalPrice = 0.0;
    for (StockDataEntity entity : entities){
      if (entity.getDate().isEqual(LocalDate.now())) {
      Double price = entity.getRegularMarketPrice();  
      totalPrice += price;
      entity.setAveragePrice(totalPrice/entity.getId());  //have to divide data number not id
      output.add(entity);
    }
  }
    return output;
      
    }
  

  @Override
  public void clearData(){
    List<StockDataEntity> entities = sdRepository.findAll();
    if (entities.size() > 0){
      sdRepository.deleteAll();
    }
  }
}
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

