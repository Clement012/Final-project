package com.example.sb.bc_yahoo_finance.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.sb.bc_yahoo_finance.controller.RealTimeOperation;
import com.example.sb.bc_yahoo_finance.model.Response;
import com.example.sb.bc_yahoo_finance.service.RealTimeDataService;

@RestController
public class RealTimeController implements RealTimeOperation {

  @Autowired
  private RealTimeDataService realTimeDataService;
  
  @Override
  public List<Response> getResponse(){
    return realTimeDataService.getResponse();
  }
}
