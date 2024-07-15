package com.example.fp.frontend.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.fp.frontend.controller.HistoryOperation;

@Controller
public class Test {
  
  @GetMapping(value = "/test")
  public String test(Model model){
    // main
    // model.addAttribute("test", HistoryOperation.test());
    return "test";
  }
}
