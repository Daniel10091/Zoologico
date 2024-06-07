package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EspecieDTO {
    
  private Long especieCode;
  private String especieNome;
  private String especieDescricao;
  private String especieClassificacao;
  private String especieOrigem;

}
