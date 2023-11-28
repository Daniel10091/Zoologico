package com.example.zoologico.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.dto.AnimalDTO;
import com.example.zoologico.domain.exception.EmptyListException;
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
  public List<AnimalDTO> getAllAnimais() {
    List<Animal> animals = null;
    List<AnimalDTO> animalsDTO =  new ArrayList<AnimalDTO>();

    animals = animalRepository.findAll();

    animals.stream().forEach(animal -> {
      AnimalDTO animalDTO = new AnimalDTO();
      Especie especie = new Especie();
      Zoologico zoologico = new Zoologico();

      animalDTO.setAnimalCode(animal.getId());
      animalDTO.setAnimalNome(animal.getNome());
      animalDTO.setAnimalDataNascimento(animal.getDataNascimento());
      animalDTO.setAnimalCor(animal.getCor());
      animalDTO.setAnimalTamanho(animal.getTamanho());
      animalDTO.setAnimalDescricao(animal.getDescricao());
      
      especie = especieService.findEspecieById(animal.getEspecieId());
      
      animalDTO.setEspecieCode(especie.getId());
      animalDTO.setEspecieNome(especie.getNome());
      animalDTO.setEspecieDescricao(especie.getDescricao());
      animalDTO.setEspecieClassificacao(especie.getClassificacao());
      animalDTO.setEspecieOrigem(especie.getOrigem());

      zoologico = zoologicoService.findZoologicoById(animal.getZoologicoId());

      animalDTO.setZoologicoCode(zoologico.getId());
      animalDTO.setZoologicoCnpj(zoologico.getCnpj());
      animalDTO.setZoologicoNome(zoologico.getNome());

      animalDTO.setEnderecoCode(zoologico.getEnderecoId());
      animalDTO.setFornecedorCode(zoologico.getFornecedorId());

      animalsDTO.add(animalDTO);
    });

    if (animals.isEmpty()) 
      throw new EmptyListException("Nenhum animal registrado");

    return animalsDTO;
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
    Especie especie = null;
    Zoologico zoologico = null;

    especie = especieService.findEspecieById(animal.getEspecieId());

    if (especie == null) 
      throw new EntityNotFoundException("A especie " + animal.getEspecieId() + " não foi encontrada");
      
    zoologico = zoologicoService.findOneZoologicoById(animal.getZoologicoId());

    if (zoologico == null) 
      throw new EntityNotFoundException("O zoologico " + animal.getZoologicoId() + " não foi encontrado");

    // if (zoologico.equals(animal.getZoologicoId())) {
    //   throw new RequestErrorException("O animal " + animal.getNome() + " já está registrado no zoologico " + zoologico.getNome());
    // }

    // if (zoologico.getAnimais().size() >= zoologico.getCapacidadeMaxima()) {
    //   throw new RequestErrorException("O zoologico " + zoologico.getNome() + " já está com a capacidade máxima");
    // }

    try {
      animal = animalRepository.save(animal);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save animal: " + e.getMessage());
    }

    return animal;
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

    animalToDelete = this.findAnimalById(id);

    try {
      animalRepository.deleteById(animalToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete animal: " + e.getMessage());
    }
  }
  
}
