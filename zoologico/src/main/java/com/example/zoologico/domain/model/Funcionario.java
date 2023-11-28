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
@Table(name = "funcionario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "SOBRENOME")
  private String sobrenome;

  @Column(name = "NU_CPF")
  private String nuCpf;

  @Column(name = "ENDERECO_ID")
  private Long enderecoId;

  @Column(name = "ZOOLOGICO_ID")
  private Long zoologicoId;

  @Column(name = "CARGO_ID")
  private Long cargoId;

  @Column(name = "DEPARTAMENTO_ID")
  private Long departamentoId;

}
