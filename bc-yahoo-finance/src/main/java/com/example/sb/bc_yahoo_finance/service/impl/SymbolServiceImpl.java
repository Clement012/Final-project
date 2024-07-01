package com.example.sb.bc_yahoo_finance.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.sb.bc_yahoo_finance.config.Config;
import com.example.sb.bc_yahoo_finance.entity.SymbolEntity;
import com.example.sb.bc_yahoo_finance.infra.RedisHelper;
import com.example.sb.bc_yahoo_finance.mapper.SymbolMapper;
import com.example.sb.bc_yahoo_finance.model.Symbol;
import com.example.sb.bc_yahoo_finance.repository.SymbolRepository;
import com.example.sb.bc_yahoo_finance.service.SymbolService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class SymbolServiceImpl implements SymbolService{

  @Autowired
  private Config config;

  @Autowired 
  private SymbolRepository symbolRepository;

  @Autowired
  private SymbolMapper symbolMapper;

  @Autowired 
  private RedisHelper redisHelper;
  
  
  @Override
  public void addSymbol(){
    List<Symbol> symbols = config.getSymbols();
        symbols.stream()
       .map(s -> symbolMapper.map(s))
       .forEach(s -> symbolRepository.save(s));
  }

  @Override
  public List<Symbol> getSymbols() throws JsonProcessingException{

    List<Symbol> symbolsList = config.getSymbols();
    if (symbolsList != null){
      return symbolsList;
    } else {
    this.redisHelper.set("STOCK-LIST", symbolsList);
    return symbolsList;
    }
  }

  @Override
  public void clearSymbols(){
    List<SymbolEntity> se = symbolRepository.findAll();
    if (se.size() > 0){
      symbolRepository.deleteAll();
    }
  }
}

