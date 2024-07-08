package com.example.bc_stock_web.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bc_stock_web.controller.HistoryOperation;


@Controller
public class ChartController {

  // @Autowired
  // private RealTimeOperation realTimeOperation;
  
  @GetMapping(value = "/realtime")
  public String realtime(@RequestParam("symbol") String symbol,Model model) {
      model.addAttribute("symbol", symbol);
    // List<RealTimeEntity> getBidAskEntities = realTimeOperation.getBidAskEntity();
    // List<StockBidAskDTO> bidasks = realTimeOperation.getBidAsks();
    // model.addAttribute("bidasks", bidasks);
    // model.addAttribute("hello", RealTimeOperation.test());
    return "realtime";
  }

  // @GetMapping(value = "/testtest")
  // public String test(Model model){
  //   String symbol = "0388.HK";
  //   List<StockBidAskDTO> bidasks = realTimeOperation.getBidAsks();
  //   model.addAttribute("bidasks", bidasks);
  //   return "realtimebackup";
  // }
}

