package com.example.zoologico.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historico_saude")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoricoSaude {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  
  @Column(name = "DATA_REGISTRO")
  private String dataRegistro;
  
  @Column(name = "OBSERVACOES_MEDICAS")
  private String observacoesMedicas;
  
  @Column(name = "PROCEDIMENTO_MEDICO")
  private String procedimentoMedico;
  
  @Column(name = "RESULTADO_EXAME")
  private String resultadoExame;
  
}
