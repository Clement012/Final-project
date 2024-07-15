package com.example.fp.frontend.view;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.fp.frontend.controller.RealTimeOperation;
import com.example.fp.frontend.model.instant.BidAsk;


@Controller
public class ChartController {

  @Autowired
  private RealTimeOperation realTimeOperation;
  
  @GetMapping(value = "/realtime")
  public String realtime(@RequestParam("symbol") String symbol,Model model) {
    model.addAttribute("symbol", symbol);
    List<BidAsk> bidAsks = realTimeOperation.getBidAsks(symbol);
    model.addAttribute("bidasks", bidAsks);
    return "realtime";
  }
}
