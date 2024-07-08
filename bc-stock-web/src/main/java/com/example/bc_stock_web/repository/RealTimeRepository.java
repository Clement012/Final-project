package com.example.bc_stock_web.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bc_stock_web.entity.RealTimeEntity;

@Repository
public interface RealTimeRepository extends JpaRepository<RealTimeEntity ,Long>{
  
  Optional<List<RealTimeEntity>> findBySymbol(String symbol);
}
