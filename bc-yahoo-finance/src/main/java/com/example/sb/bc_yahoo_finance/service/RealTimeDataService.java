package com.example.sb.bc_yahoo_finance.service;

import java.util.List;
import com.example.sb.bc_yahoo_finance.model.Response;

public interface RealTimeDataService {
  
  List<Response> getResponse();
}
