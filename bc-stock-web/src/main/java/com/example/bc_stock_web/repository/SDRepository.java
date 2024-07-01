package com.example.bc_stock_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bc_stock_web.entity.StockDataEntity;

@Repository
public interface SDRepository extends JpaRepository<StockDataEntity ,Long>{
  
}
