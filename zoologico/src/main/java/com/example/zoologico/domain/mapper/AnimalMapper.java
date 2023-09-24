package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.AnimalDTO;
import com.example.zoologico.domain.model.Animal;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
  
  @Mapping(source = "animal.id", target = "animalcode")
  @Mapping(source = "animal.nome", target = "animalNome")
  @Mapping(source = "animal.dataNascimento", target = "animalDataNascimento")
  @Mapping(source = "animal.cor", target = "animalCor")
  @Mapping(source = "animal.tamanho", target = "animalTamanho")
  @Mapping(source = "animal.descricao", target = "animalDescricao")
  @Mapping(source = "animal.especieId", target = "especieCode")
  @Mapping(source = "animal.zoologicoId", target = "zoologicoCode")
  public AnimalDTO toDto(Animal animal);

  @InheritInverseConfiguration
  public Animal toEntity(AnimalDTO animalDTO);

}
