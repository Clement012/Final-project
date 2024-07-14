package com.example.fp.frontend.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ViewHistoryController {

  @GetMapping(value = "/history")
  public String historydata(@RequestParam String symbol,Model model){
    model.addAttribute("symbol", symbol);
    return "historydata";  
  }
}
