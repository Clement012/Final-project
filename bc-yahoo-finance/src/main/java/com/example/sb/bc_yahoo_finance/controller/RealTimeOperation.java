package com.example.sb.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.sb.bc_yahoo_finance.model.Response;

public interface RealTimeOperation {
  
  @GetMapping(value ="/real")
  List<Response> getResponse();
}
