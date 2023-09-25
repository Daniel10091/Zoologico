package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.FuncionarioDTO;
import com.example.zoologico.domain.model.Funcionario;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {
  
  @Mapping(source = "funcionario.id", target = "code")
  @Mapping(source = "funcionario.nome", target = "nome")
  @Mapping(source = "funcionario.sobrenome", target = "sobrenome")
  @Mapping(source = "funcionario.enderecoId", target = "enderecoId")
  @Mapping(source = "funcionario.zoologicoId", target = "zoologicoId")
  @Mapping(source = "funcionario.cargoId", target = "cargoId")
  @Mapping(source = "funcionario.departamentoId", target = "departamentoId")
  @Mapping(source = "funcionario.nuCpf", target = "nuCpf")
  public FuncionarioDTO toDto(Funcionario funcionario);

  @InheritInverseConfiguration
  public Funcionario toEntity(FuncionarioDTO funcionarioDTO);

}
