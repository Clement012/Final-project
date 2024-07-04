package com.example.bc_stock_web.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bc_stock_web.controller.HistoryOperation;
import com.example.bc_stock_web.controller.StockPriceOperation;
import com.example.bc_stock_web.dto.CandleStickDTO;
import com.example.bc_stock_web.dto.StockBidAskDTO;
import com.example.bc_stock_web.model.Stock;
import com.example.bc_stock_web.service.StockService;

@Controller
public class DataViewController {

  @Autowired
  private StockPriceOperation stockPriceOperation;

  @Autowired
  private HistoryOperation historyOperation;
  
  @GetMapping(value = "/realtime")
  public String index(Model model){
    List<StockBidAskDTO> bidasks = stockPriceOperation.getBidAsks();
    model.addAttribute("bidasks", bidasks);
    return "realtime";
  }

  @GetMapping(value = "/history")
  public String historydata(Model model){
    List<CandleStickDTO> prices = historyOperation.getPrice();
    model.addAttribute("prices", prices);
    return "historydata";  
  }

  @GetMapping(value = "/frontpage")
  public String frontpage(Model model){
    model.addAttribute("hello", StockPriceOperation.test());
    return "frontpage";
  }
}
