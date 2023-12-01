package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.CargoDTO;
import com.example.zoologico.domain.dto.FuncionarioDTO;
import com.example.zoologico.domain.model.Cargo;

@Mapper(componentModel = "spring")
public interface CargoMapper {
  
  @Mapping(source = "cargo.id", target = "cargoCode")
  @Mapping(source = "cargo.titulo", target = "cargoTitulo")
  @Mapping(source = "cargo.descricao", target = "cargoDescricao")
  @Mapping(source = "cargo.salarioBase", target = "salarioBase")
  public CargoDTO _toDto(Cargo cargo);

  @InheritInverseConfiguration
  public Cargo _toEntity(CargoDTO cargoDTO);
  
  @Mapping(source = "cargo.id", target = "cargoCode")
  @Mapping(source = "cargo.titulo", target = "cargoTitulo")
  @Mapping(source = "cargo.descricao", target = "cargoDescricao")
  @Mapping(source = "cargo.salarioBase", target = "salarioBase")
  public FuncionarioDTO toDto(Cargo cargo);

  @InheritInverseConfiguration
  public Cargo toEntity(FuncionarioDTO funcionarioDTO);

}
