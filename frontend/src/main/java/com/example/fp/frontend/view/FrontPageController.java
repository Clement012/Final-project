package com.example.fp.frontend.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.fp.frontend.controller.RealTimeOperation;
import com.example.fp.frontend.model.instant.Stock;


@Controller
public class FrontPageController {
  
  @Autowired
  private RealTimeOperation realTimeOperation;

  @GetMapping(value = "/frontpage")
  public String frontpage(Model model){
    List<Stock> stocks = realTimeOperation.getDataList();
    model.addAttribute("stocks", stocks);
    return "frontpage";
  }
}
