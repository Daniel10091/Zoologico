package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.FornecedorDTO;
import com.example.zoologico.domain.model.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
  
  @Mapping(source = "endereco.id", target = "enderecoCode")
  @Mapping(source = "endereco.pais", target = "pais")
  @Mapping(source = "endereco.estado", target = "estado")
  @Mapping(source = "endereco.cidade", target = "cidade")
  @Mapping(source = "endereco.logradouro", target = "logradouro")
  @Mapping(source = "endereco.complemento", target = "complemento")
  public FornecedorDTO toDto(Endereco endereco);

  @InheritInverseConfiguration
  public Endereco toEntiry(FornecedorDTO fornecedorDTO);

}
