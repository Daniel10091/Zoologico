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

@Entity(name = "fornecedor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Fornecedor {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NU_CNPJ")
  private String cnpj;

  @Column(name = "RAZAO_SOCIAL")
  private String razaoSocial;

  @Column(name = "ENDERECO_ID")
  private Long enderecoId;

}
