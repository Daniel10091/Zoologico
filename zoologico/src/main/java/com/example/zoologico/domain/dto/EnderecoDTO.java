package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO {
  
  private Long enderecoCode;
  private String pais;
  private String estado;
  private String cidade;
  private String logradouro;
  private String complemento;

}
