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
@Table(name = "especie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Especie {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "DESCRICAO")
  private String descricao;

  @Column(name = "CLASSIFICACAO")
  private String classificacao;

  @Column(name = "ORIGEM")
  private String origem;

}
