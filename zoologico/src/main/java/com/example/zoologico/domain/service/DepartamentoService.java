package com.example.zoologico.domain.service;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Departamento;
import com.example.zoologico.domain.repository.DepartamentoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartamentoService {
  
  private final DepartamentoRepository departamentoRepository;

  public DepartamentoService(DepartamentoRepository departamentoRepository) {
    this.departamentoRepository = departamentoRepository;
  }

  /**
   * Find a Departamento by {@code id}
   * 
   * @param id
   * @return <b>{@code Departamento}</b>
   */
  public Departamento findDepartamentoById(Long id) {
    return departamentoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Funcionário com o id " + id + " não foi encontrado"));
  }

}
