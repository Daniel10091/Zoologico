package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zoologico.domain.model.HistoricoSaude;

public interface HistoricoSaudeRepository extends JpaRepository<HistoricoSaude, Long> {

  Optional<HistoricoSaude> findById(Long id);
  
}
