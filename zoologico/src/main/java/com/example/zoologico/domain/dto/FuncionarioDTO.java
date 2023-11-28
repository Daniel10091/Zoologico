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
  private String nuCpf;

  private Long enderecoCode;
  private String pais;
  private String estado;
  private String cidade;
  private String logradouro;
  private String complemento;
  
  private Long zoologicoCode;

  private Long cargoCode;

  private Long departamentoCode;

}
