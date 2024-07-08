package com.example.bc_stock_web.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bc_stock_web.controller.RealTimeOperation;
import com.example.bc_stock_web.dto.StockDTO;

@Controller
public class FrontPageController {
  
  @Autowired
  private RealTimeOperation realTimeOperation;

  @GetMapping(value = "/frontpage")
  public String frontpage(Model model){
    List<StockDTO> stocks = realTimeOperation.getDataList();
    model.addAttribute("stocks", stocks);
    return "frontpage";
  }

  // @GetMapping("/")  //testing
  //   public String test(Model model) {
  //       return "test";
  //   }
}
