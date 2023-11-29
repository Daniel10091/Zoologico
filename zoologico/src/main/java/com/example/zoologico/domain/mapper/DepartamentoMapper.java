package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.FuncionarioDTO;
import com.example.zoologico.domain.model.Departamento;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {
  
  @Mapping(source = "departamento.id", target = "departamentoCode")
  // @Mapping(source = "departamento.nome", target = "departamentoNome")
  // @Mapping(source = "departamento.descricao", target = "departamentoDescricao")
  public FuncionarioDTO toDto(Departamento departamento);

  @InheritInverseConfiguration
  public Departamento toEntity(FuncionarioDTO funcionarioDTO);

}
