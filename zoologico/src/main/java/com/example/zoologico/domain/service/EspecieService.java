package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Especie;
import com.example.zoologico.domain.repository.EspecieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecieService {
  
  private final EspecieRepository especieRepository;

  public EspecieService(EspecieRepository especieRepository) {
    this.especieRepository = especieRepository;
  }
  
  /**
   * Get all Especies
   * 
   * @return {@code List<Especie>}
   */
  public List<Especie> getAllEnderecos() {
    List<Especie> especies = null;

    especies = especieRepository.findAll();

    if (especies.isEmpty()) 
      throw new EntityNotFoundException("Nenhum espécies encontrado");

    return especies;
  }

  /**
   * Find a Especie by {@code id}
   * 
   * @param id
   * @return <b>{@code Especie}</b>
   */
  public Especie findEspecieById(Long id) {
    return especieRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Espécie com o id " + id + " não foi encontrado"));
  }

  /**
   * Register a new Especie
   * 
   * @param especie
   * @return <b>{@code Especie}</b>
   */
  public Especie registerEspecie(Especie especie) {
    Especie newEspecie = null;
    
    try {
      newEspecie = especieRepository.save(especie);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save especie: " + e.getMessage());
    }

    return newEspecie;
  }

  /**
   * Update a Especie by {@code id}
   * 
   * @param id
   * @param especie
   * @return <b>{@code Especie}</b>
   */
  public Especie updateEspecie(Long id, Especie especie) {
    Especie especieToUpdate = new Especie();
    Especie especieReturn = null;

    if (especieRepository.findById(id).isPresent())
      throw new EntityNotFoundException("Espécie com o id " + id + " não foi encontrado");

    especieToUpdate.setNome(especie.getNome());
    especieToUpdate.setDescricao(especie.getDescricao());
    especieToUpdate.setClassificacao(especie.getClassificacao());
    especieToUpdate.setOrigem(especie.getOrigem());

    try {
      especieReturn = especieRepository.save(especieToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update especie: " + e.getMessage());
    }

    return especieReturn;
  }

  /**
   * Delete a Especie by {@code id}
   * 
   * @param id
   * @return <b>{@code Especie}</b>
   */
  public void deleteEspecieById(Long id) throws Exception {
    Especie especieToDelete = null;

    especieToDelete = findEspecieById(id);

    try {
      especieRepository.deleteById(especieToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete especie: " + e.getMessage());
    }
  }

}
