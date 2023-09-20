package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.AnimalDTO;
import com.example.zoologico.domain.model.Animal;

@Mapper
public interface AnimalMapper {
  
  @Mapping(source = "animal.id", target = "animalcode")
  @Mapping(source = "animal.nome", target = "animalNome")
  @Mapping(source = "animal.dataNascimento", target = "animalDataNascimento")
  @Mapping(source = "animal.cor", target = "animalCor")
  @Mapping(source = "animal.tamanho", target = "animalTamanho")
  @Mapping(source = "animal.descricao", target = "animalDescricao")
  @Mapping(source = "animal.especieId", target = "especieCode")
  public AnimalDTO toDto(Animal departamento);

  @InheritInverseConfiguration
  public Animal toEntiry(AnimalDTO animalDTO);

}
