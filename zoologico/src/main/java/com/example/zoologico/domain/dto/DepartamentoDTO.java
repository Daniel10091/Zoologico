package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartamentoDTO {
  
  private Long departamentoCode;
  private String departamentoNome;
  private String departamentoDescricao;

}
