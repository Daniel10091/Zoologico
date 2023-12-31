package com.example.zoologico.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.zoologico.domain.dto.FornecedorDTO;
import com.example.zoologico.domain.model.Fornecedor;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
  
  @Mapping(source = "fornecedor.id", target = "fornecedorCode")
  @Mapping(source = "fornecedor.cnpj", target = "fornecedorCnpj")
  @Mapping(source = "fornecedor.razaoSocial", target = "fornecedorRazaoSocial")
  @Mapping(source = "fornecedor.enderecoId", target = "enderecoCode")
  public FornecedorDTO toDto(Fornecedor fornecedor);

  @InheritInverseConfiguration
  public Fornecedor toEntity(FornecedorDTO fornecedorDTO);

}
