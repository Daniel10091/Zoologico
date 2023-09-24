package com.example.zoologico.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zoologico.domain.dto.AnimalDTO;
import com.example.zoologico.domain.mapper.AnimalMapper;
import com.example.zoologico.domain.service.AnimalService;

@RestController
@RequestMapping("/animais")
public class AnimalController {
  
  private final AnimalService animalService;
  private final AnimalMapper animalMapper;

  public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
    this.animalService = animalService;
    this.animalMapper = animalMapper;
  }

  // Get all Animals
  @GetMapping(value = "/animal")
  public ResponseEntity<List<AnimalDTO>> getAnimals() {
    var result = animalService.getAllAnimals().stream().map(animalMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a Animal by id
  @GetMapping(value = "/animal/{id}")
  public ResponseEntity<AnimalDTO> findAnimal(@PathVariable(value = "id") Long id) {
    var result = animalService.findAnimalById(id);
    return ResponseEntity.ok(animalMapper.toDto(result));
  }

  // Register a new Animal
  @PostMapping("/animal")
  public ResponseEntity<AnimalDTO> registerAnimal(@RequestBody AnimalDTO animal) {
    var result = animalService.registerAnimal(animalMapper.toEntity(animal));
    return ResponseEntity.ok(animalMapper.toDto(result));
  }

  // Delete a Animal by id
  @DeleteMapping(value = "/animal/{id}")
  public ResponseEntity<?> deleteAnimal(@PathVariable(value = "id") Long id) throws Exception {
    animalService.deleteAnimal(id);
    return ResponseEntity.ok().build();
  }

}
