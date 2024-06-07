package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.AnimalDTO;
import com.example.zoologico.domain.dto.EspecieDTO;
import com.example.zoologico.domain.model.Especie;

@Mapper(componentModel = "spring")
public interface EspecieMapper {
  
  @Mapping(source = "id", target = "especieCode")
  @Mapping(source = "nome", target = "especieNome")
  @Mapping(source = "descricao", target = "especieDescricao")
  @Mapping(source = "classificacao", target = "especieClassificacao")
  @Mapping(source = "origem", target = "especieOrigem")
  public EspecieDTO toDto(Especie especie);

  @InheritInverseConfiguration
  public Especie toEntity(EspecieDTO especieDTO);
  
  @Mapping(source = "especie.id", target = "especieCode")
  @Mapping(source = "especie.nome", target = "especieNome")
  @Mapping(source = "especie.descricao", target = "especieDescricao")
  @Mapping(source = "especie.classificacao", target = "especieClassificacao")
  @Mapping(source = "especie.origem", target = "especieOrigem")
  public AnimalDTO _toDto(Especie especie);

  @InheritInverseConfiguration
  public Especie _toEntity(AnimalDTO animalDTO);

}
