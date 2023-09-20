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
@Table(name = "animal")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Animal {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "DATA_NASCIMENTO")
  private String dataNascimento;

  @Column(name = "COR")
  private String cor;

  @Column(name = "TAMANHO")
  private String tamanho;

  @Column(name = "DESCRICAO")
  private String descricao;

  @Column(name = "ESPECIE_ID")
  private Long especieId;

  @Column(name = "ZOOLOGICO_ID")
  private Long zoologicoId;

}
