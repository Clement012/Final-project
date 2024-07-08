package com.example.bc_stock_web.controller.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bc_stock_web.model.StockPrice;

@RestController
public class TestController {
  
  @GetMapping(value = "/five")
  public List<StockPrice> getFiveMinuteData() {
    return Arrays.asList( //
        new StockPrice(2024, 5, 27, 9, 0, 150.0), //
        new StockPrice(2024, 5, 27, 9, 5, 151.0), //
        new StockPrice(2024, 5, 27, 9, 10, 152.0), //
        new StockPrice(2024, 5, 27, 9, 15, 153.0), //
        new StockPrice(2024, 5, 27, 9, 20, 154.0), //
        new StockPrice(2024, 5, 27, 9, 25, 155.0), //
        new StockPrice(2024, 5, 27, 9, 30, 156.0), //
        new StockPrice(2024, 5, 27, 9, 35, 157.0), //
        new StockPrice(2024, 5, 27, 9, 40, 158.0), //
        new StockPrice(2024, 5, 27, 9, 45, 159.0), //
        new StockPrice(2024, 5, 27, 9, 50, 159.0), //
        new StockPrice(2024, 5, 27, 9, 55, 159.0), //
        new StockPrice(2024, 5, 27, 10, 0, 154.0), //
        new StockPrice(2024, 5, 27, 10, 5, 158.0), //
        new StockPrice(2024, 5, 27, 10, 10, 160.0), //
        new StockPrice(2024, 5, 27, 10, 15, 170.0), //
        new StockPrice(2024, 5, 27, 10, 20, 159.0), //
        new StockPrice(2024, 5, 27, 10, 25, 158.0), //
        new StockPrice(2024, 5, 27, 10, 30, 143.0), //
        new StockPrice(2024, 5, 27, 10, 35, 160.0), //
        new StockPrice(2024, 5, 27, 10, 40, 190.0), //
        new StockPrice(2024, 5, 27, 10, 45, 149.0), //
        new StockPrice(2024, 5, 27, 10, 50, 170.0), //
        new StockPrice(2024, 5, 27, 10, 55, 168.0), //
        new StockPrice(2024, 5, 27, 11, 0, 159.0) //
    );
  }
}
