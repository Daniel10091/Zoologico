package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoricoSaudeDTO {
  
  private String code;
  private String dataRegistro;
  private String observacoesMedicas;
  private String procedimentoMedico;
  private String resultadoExame;

}
