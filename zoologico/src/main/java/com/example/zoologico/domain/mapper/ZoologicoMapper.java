package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.ZoologicoDTO;
import com.example.zoologico.domain.model.Zoologico;

@Mapper(componentModel = "spring")
public interface ZoologicoMapper {
  
  @Mapping(source = "zoologico.id", target = "zoologicoCode")
  @Mapping(source = "zoologico.cnpj", target = "zoologicoCnpj")
  @Mapping(source = "zoologico.nome", target = "zoologicoNome")
  @Mapping(source = "zoologico.enderecoId", target = "enderecoCode")
  @Mapping(source = "zoologico.fornecedorId", target = "fornecedorCode")
  public ZoologicoDTO toDto(Zoologico zoologico);

  @InheritInverseConfiguration
  public Zoologico toEntiry(ZoologicoDTO zoologicoDTO);

}
