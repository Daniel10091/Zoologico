package com.example.zoologico.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnimalDTO {

  private Long animalCode;
  private String animalNome;
  private LocalDate animalDataNascimento;
  private String animalCor;
  private String animalTamanho;
  private String animalDescricao;
  private Long especieCode;
  private String especieNome;
  private String especieDescricao;
  private String especieClassificacao;
  private String especieOrigem;
  private Long zoologicoCode;
  private String zoologicoCnpj;
  private String zoologicoNome;
  private Long enderecoCode;
  private Long fornecedorCode;

}
