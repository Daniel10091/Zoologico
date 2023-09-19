package com.example.zoologico.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "zoologico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Zoologico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NU_CNPJ")
  private String cnpj;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "ENDERECO_ID")
  private Long enderecoId;

  @Column(name = "FORNECEDOR_ID")
  private Long fornecedorId;
  
}
