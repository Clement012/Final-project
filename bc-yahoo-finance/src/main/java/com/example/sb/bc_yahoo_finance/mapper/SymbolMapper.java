package com.example.sb.bc_yahoo_finance.mapper;

import org.springframework.stereotype.Component;
import com.example.sb.bc_yahoo_finance.entity.SymbolEntity;
import com.example.sb.bc_yahoo_finance.model.Symbol;

@Component
public class SymbolMapper {
  public SymbolEntity map(Symbol symbol){
    return SymbolEntity.builder()
          .symbol(symbol.getSymbol())
          .build();
  }
}
