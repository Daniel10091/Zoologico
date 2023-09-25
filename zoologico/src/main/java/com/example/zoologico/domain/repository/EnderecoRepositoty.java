package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zoologico.domain.model.Endereco;

@Repository
public interface EnderecoRepositoty extends JpaRepository<Endereco, Long>{
  
  Optional<Endereco> findById(Long id);

}
