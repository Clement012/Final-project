package com.example.sb.bc_yahoo_finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sb.bc_yahoo_finance.entity.SymbolEntity;

@Repository
public interface SymbolRepository extends JpaRepository<SymbolEntity,Long>{
  
}
