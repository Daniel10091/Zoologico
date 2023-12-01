package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EmptyListException;
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
   * Get all Cargos
   * 
   * @return {@code List<Cargo>}
   */
  public List<Cargo> getAllCargos() {
    List<Cargo> cargos = null;

    cargos = funcionarioRepository.findAll();

    if (cargos.isEmpty()) 
      throw new EmptyListException("Nenhum cargo registrado");

    return cargos;
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
