package com.example.bc_stock_web.controller;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.bc_stock_web.model.StockPrice;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.dto.StockDTO;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.entity.RealTimeEntity;

public interface RealTimeOperation {

   @GetMapping(value = "/datalist")
   List<StockDTO> getDataList();

   @GetMapping(value = "/bidask")
   List<StockBidAskDTO> getBidAsks();

   @GetMapping(value = "/five-minute/{symbol}")
   List<RealTimeEntity> getPrice(@PathVariable String symbol) throws NotFoundException;

   @GetMapping(value = "/ma/{symbol}")
   List<RealTimeEntity> getFive(@PathVariable String symbol) throws NotFoundException;

   @GetMapping(value = "/bidask/hke")  //0388
   List<StockBidAskDTO> getHKEBidAsks();

   @GetMapping(value = "/entitybidask/{symbol}")
   List<RealTimeEntity> getBidAskEntity(@PathVariable String symbol) throws NotFoundException;

   // @GetMapping(value = "/bid")
   // List<StockBidAskDTO> getBidAskDTOs();

   // static String test(){
   //    return "hello";
   // }
}
