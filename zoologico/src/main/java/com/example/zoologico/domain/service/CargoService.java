package com.example.zoologico.domain.service;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Cargo;
import com.example.zoologico.domain.repository.CargoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CargoService {

  private final CargoRepository funcionarioRepository;
  
  public CargoService(CargoRepository funcionarioRepositoty) {
    this.funcionarioRepository = funcionarioRepositoty;
  }

  /**
   * Find a Cargo by {@code id}
   * 
   * @param id
   * @return <b>{@code Cargo}</b>
   */
  public Cargo findCargoById(Long id) {
    return funcionarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cargo com o id " + id + " não foi encontrado"));
  }

}
