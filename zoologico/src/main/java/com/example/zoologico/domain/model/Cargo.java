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
@Table(name = "cargo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cargo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "TITULO")
  private String titulo;

  @Column(name = "DESCRICAO")
  private String descricao;

  @Column(name = "SALARIO_BASE")
  private Double salarioBase;

}
