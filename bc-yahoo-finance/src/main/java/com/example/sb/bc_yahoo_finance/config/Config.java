package com.example.sb.bc_yahoo_finance.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.example.sb.bc_yahoo_finance.infra.RedisHelper;
import com.example.sb.bc_yahoo_finance.model.Symbol;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;

@Configuration
public class Config {
  
  private final List<Symbol> symbols = new ArrayList<>();

  public List<Symbol> getSymbols(){
    return this.symbols;
  }
  
  @PostConstruct
  public void addSymbol(){
    symbols.add(new Symbol("0388.HK"));
    symbols.add(new Symbol("0700.HK"));
  }

  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
   ObjectMapper objectMapper(){
    return new ObjectMapper();
   }

  @Bean
   RedisHelper redisHelper(RedisConnectionFactory factory, ObjectMapper objectMapper){
    return new RedisHelper(factory , objectMapper);
   }
}
