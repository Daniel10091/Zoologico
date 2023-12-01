package com.example.zoologico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CargoDTO {
  
  private Long cargoCode;
  private String cargoTitulo;
  private String cargoDescricao;
  private String salarioBase;

}
