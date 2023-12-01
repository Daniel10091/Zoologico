package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EmptyListException;
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
   * Get all Departamentos
   * 
   * @return {@code List<Departamento>}
   */
  public List<Departamento> getAllDepartamentos() {
    List<Departamento> departamentos = null;

    departamentos = departamentoRepository.findAll();

    if (departamentos.isEmpty()) 
      throw new EmptyListException("Nenhum departamento registrado");

    return departamentos;
  }

  /**
   * Find a Departamento by {@code id}
   * 
   * @param id
   * @return <b>{@code Departamento}</b>
   */
  public Departamento findDepartamentoById(Long id) {
    return departamentoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Departamento com o id " + id + " não foi encontrado"));
  }

}
