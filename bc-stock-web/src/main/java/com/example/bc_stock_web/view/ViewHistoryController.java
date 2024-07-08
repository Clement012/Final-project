package com.example.bc_stock_web.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bc_stock_web.controller.HistoryOperation;
import com.example.bc_stock_web.dto.CandleStickDTO;

@Controller
public class ViewHistoryController {
  
  // @Autowired
  // private HistoryOperation historyOperation;

  @GetMapping(value = "/history")
  public String historydata(@RequestParam String symbol,Model model){
    // List<CandleStickDTO> prices = historyOperation.getPrice();
    // model.addAttribute("prices", prices);
    // model.addAttribute("hello", HistoryOperation.test());
    model.addAttribute("symbol", symbol);
    return "historydata";  
  }
}
