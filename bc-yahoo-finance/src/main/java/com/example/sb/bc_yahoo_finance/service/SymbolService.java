package com.example.sb.bc_yahoo_finance.service;

import java.util.List;
import com.example.sb.bc_yahoo_finance.model.Symbol;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SymbolService {

  void addSymbol();

  List<Symbol> getSymbols()throws JsonProcessingException;

  void clearSymbols();
  // List<Stock> getStocks();
}
