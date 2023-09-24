package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.AnimalDTO;
import com.example.zoologico.domain.model.Especie;

@Mapper(componentModel = "spring")
public interface EspecieMapper {
  
  @Mapping(source = "especie.id", target = "especieCode")
  @Mapping(source = "especie.nome", target = "especieNome")
  @Mapping(source = "especie.descricao", target = "especieDescricao")
  @Mapping(source = "especie.classificacao", target = "especieClassificacao")
  @Mapping(source = "especie.origem", target = "especieOrigem")
  public AnimalDTO toDto(Especie especie);

  @InheritInverseConfiguration
  public Especie toEntity(AnimalDTO animalDTO);

}
