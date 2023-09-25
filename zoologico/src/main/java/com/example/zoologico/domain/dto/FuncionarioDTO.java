package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuncionarioDTO {

  private Long code;
  private String nome;
  private String sobrenome;
  private String enderecoId;
  private String zoologicoId;
  private String cargoId;
  private String departamentoId;
  private String nuCpf;

  private String pais;
  private String estado;
  private String cidade;
  private String logradouro;
  private String complemento;
  
  private Long zoologicoCode;
  private String zoologicoCnpj;
  private String zoologicoNome;
  private Long enderecoCode;
  private Long fornecedorCode;

  private Long cargoCode;
  private String cargoTitulo;
  private String cargoDescricao;
  private Double salarioBase;

  private Long departamentoCode;
  private String departamentoNome;
  private String departamentoDescricao;

}
