package com.example.sb.bc_yahoo_finance.infra;

import java.time.Duration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisHelper {
  // Encapsulate RedisTemplate.class
  // 1. remove generics <String,String>
  // 2. remove opsforvlaue
  // 3. remove initialize to object

  private RedisTemplate<String,String> redisTemplate;
  private ObjectMapper objectMapper;

  public RedisHelper(RedisConnectionFactory factory,ObjectMapper objectMapper){
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }
  
  //Serialzation
  public <T> void set(String key, T obj) throws JsonProcessingException{
    String json = this.objectMapper.writeValueAsString(obj);
    this.redisTemplate.opsForValue().set(key, json);
  }

  public <T> void set(String key, T obj,Duration duration) throws JsonProcessingException{
    String json = this.objectMapper.writeValueAsString(obj);
    this.redisTemplate.opsForValue().set(key, json, duration);
  }

  // restTemplate.getForObject(url, User.class)
  // redisTemplate.get( input key , User[].class)
  //Deserialzation
  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException{  // defind Type by inputing ppl
    String json = this.redisTemplate.opsForValue().get(key);
    return json == null ? null : this.objectMapper.readValue(key, clazz);
  }
  
}


