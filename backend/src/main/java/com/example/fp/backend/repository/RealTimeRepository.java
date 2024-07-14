package com.example.fp.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fp.backend.entity.RealTimeEntity;

public interface RealTimeRepository extends JpaRepository<RealTimeEntity,Long>{

  Optional<List<RealTimeEntity>> findBySymbol(String symbol);
} 
