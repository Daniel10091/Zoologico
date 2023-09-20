package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ZoologicoDTO {

  private Long zoologicoCode;
  private String zoologicoCnpj;
  private String zoologicoNome;
  private Long funcionarioCode;
  private Long fornecedorCode;
  private String fornecedorCnpj;
  private String fornecedorRazaoSocial;
  private Long enderecoCode;
  private String pais;
  private String estado;
  private String cidade;
  private String logradouro;
  private String complemento;

}
