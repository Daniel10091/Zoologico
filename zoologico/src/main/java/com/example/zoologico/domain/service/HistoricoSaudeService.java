package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EmptyListException;
import com.example.zoologico.domain.model.Cargo;
import com.example.zoologico.domain.model.HistoricoSaude;
import com.example.zoologico.domain.repository.HistoricoSaudeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoricoSaudeService {
  
  private final HistoricoSaudeRepository historicoSaudeRepository;
  
  public HistoricoSaudeService(HistoricoSaudeRepository historicoSaudeRepositoty) {
    this.historicoSaudeRepository = historicoSaudeRepositoty;
  }

  /**
   * Get all HistoricoSaudes
   * 
   * @return {@code List<HistoricoSaude>}
   */
  public List<HistoricoSaude> getAllHistoricoSaudes() {
    List<HistoricoSaude> historicoSaude = null;

    historicoSaude = historicoSaudeRepository.findAll();

    if (historicoSaude.isEmpty()) 
      throw new EmptyListException("Nenhum histórico de saúde registrado");

    return historicoSaude;
  }

}
