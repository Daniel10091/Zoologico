package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zoologico.domain.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long>{
  
  Optional<Especie> findById(Long id);

}
