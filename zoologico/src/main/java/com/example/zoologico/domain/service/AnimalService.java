package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Animal;
import com.example.zoologico.domain.model.Especie;
import com.example.zoologico.domain.model.Zoologico;
import com.example.zoologico.domain.repository.AnimalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AnimalService {

  private final AnimalRepository animalRepository;

  private final ZoologicoService zoologicoService;
  private final EspecieService especieService;
  
  public AnimalService(
    AnimalRepository animalRepository, 
    ZoologicoService zoologicoService, 
    EspecieService especieService
  ) {
    this.animalRepository = animalRepository;
    this.zoologicoService = zoologicoService;
    this.especieService = especieService;
  }

  /**
   * Get all Animais
   * 
   * @return {@code List<Animal>}
   */
  public List<Animal> getAllAnimais() {
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
  public Animal registerAnimal(Animal animal) {
    final Animal newAnimal = new Animal();
    Animal animalReturn = null;
    Especie especie = null;
    Zoologico zoologico = null;

    especie = especieService.findEspecieById(animal.getEspecieId());

    newAnimal.setEspecieId(especie.getId());
      
    zoologico = zoologicoService.findZoologicoById(animal.getZoologicoId());
    
    newAnimal.setZoologicoId(zoologico.getId());

    try {
      animalReturn = animalRepository.save(newAnimal);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save animal: " + e.getMessage());
    }

    return animalReturn;
  }

  /**
   * Update a Animal by {@code id}
   * 
   * @param id
   * @param Entity { <b>{@link Animal}</b> }
   * @return <b>{@code Animal}</b>
   */
  public Animal updateAnimal(Long id, Animal animal) {
    Animal animalToUpdate = null;
    Animal animalReturn = null;
    Especie especie = null;
    Zoologico zoologico = null;

    animalToUpdate = findAnimalById(id);

    animalToUpdate = animal;

    especie = especieService.findEspecieById(animal.getEspecieId());
    
    animalToUpdate.setEspecieId(especie.getId());
      
    zoologico = zoologicoService.findZoologicoById(animal.getZoologicoId());
    
    animalToUpdate.setZoologicoId(zoologico.getId());

    // animalToUpdate.setNome(animal.getNome());
    // animalToUpdate.setDataNascimento(animal.getDataNascimento());
    // animalToUpdate.setCor(animal.getCor());

    try {
      animalReturn = animalRepository.save(animalToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update animal: " + e.getMessage());
    }

    return animalReturn;
  }

  /**
   * Delete a Animal by {@code id}
   * 
   * @param id
   * @return <b>{@code void}</b>
   */
  public void deleteAnimalById(Long id) {
    Animal animalToDelete = null;

    animalToDelete = findAnimalById(id);

    try {
      animalRepository.deleteById(animalToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete animal: " + e.getMessage());
    }
  }
  
}
