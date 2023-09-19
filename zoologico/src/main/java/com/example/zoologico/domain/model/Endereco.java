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

@Entity(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "PAIS")
  private String pais;
  
  @Column(name = "ESTADO")
  private String estado;
  
  @Column(name = "CIDADE")
  private String cidade;

  @Column(name = "LOGRADOURO")
  private String logradouro;

  @Column(name = "COMPLEMENTO")
  private String complemento;

}
