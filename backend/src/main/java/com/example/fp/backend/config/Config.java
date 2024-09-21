package com.example.fp.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Config {
  
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
   ObjectMapper objectMapper() {
    return new ObjectMapper();
        // ObjectMapper mapper = new ObjectMapper();
        // mapper.registerModule(new JavaTimeModule());
        // return mapper;
    }
}
