package com.example.fp.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.fp.backend.entity.HistoryDataEntity;

@Repository 
public interface HistoryDataRepository extends JpaRepository<HistoryDataEntity,Long> {
  
  Optional<List<HistoryDataEntity>> findBySymbol(String symbol);
}
