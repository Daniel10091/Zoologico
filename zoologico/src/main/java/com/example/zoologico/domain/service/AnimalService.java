package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Animal;
import com.example.zoologico.domain.repository.AnimalRepository;
import com.example.zoologico.domain.repository.EspecieRepository;
import com.example.zoologico.domain.repository.ZoologicoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final EspecieRepository especieRepository;
  private final ZoologicoRepository zoologicoRepository;
  
  public AnimalService(
    AnimalRepository animalRepository, 
    EspecieRepository especieRepository,
    ZoologicoRepository zoologicoRepository) {
      this.animalRepository = animalRepository;
      this.especieRepository = especieRepository;
      this.zoologicoRepository = zoologicoRepository;
  }
  
  public List<Animal> getAll() {
    return animalRepository.findAll();
  }

  /**
   * Get all Animals
   * 
   * @return {@code List<Animals>}
   */
  public List<Animal> getAllAnimals() {
    List<Animal> animals = null;

    animals = animalRepository.findAll();

    if (animals.isEmpty()) 
      throw new EntityNotFoundException("Nenhum animal encontrado");

    return animals;
  }

  /**
   * Find a Animal by {@code id}
   * 
   * @param id
   * @return <b>{@code Animal}</b>
   */
  public Animal findAnimalById(Long id) {
    return animalRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Animal com o id " + id + " não foi encontrado"));
  }

  /**
   * Register a new Animal
   * 
   * @param Entity { <b>{@link Animal}</b> }
   * @return New <b>{@code Animal}</b>
   */
  public Animal registerUser(Animal animal) {
    final Animal newAnimal = new Animal();
    Animal animalReturn = null;

    this.especieRepository.findById(animal.getEspecieId())
      .map(especie -> {
        newAnimal.setEspecieId(especie.getId());
        return newAnimal;
      })
      .orElseThrow(() -> new EntityNotFoundException("Especie com o id " + animal.getEspecieId() + " não foi encontrado"));

    this.zoologicoRepository.findById(animal.getZoologicoId())
      .map(zoologico -> {
        newAnimal.setZoologicoId(zoologico.getId());
        return newAnimal;
      })
      .orElseThrow(() -> new EntityNotFoundException("Zoológico com o id " + animal.getEspecieId() + " não foi encontrado"));

    animalReturn = animalRepository.save(newAnimal);

    return animalReturn;
  }
  
}
