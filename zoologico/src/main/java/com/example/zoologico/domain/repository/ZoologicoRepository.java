package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zoologico.domain.model.Zoologico;

@Repository
public interface ZoologicoRepository extends JpaRepository<Zoologico, Long> {
  
  Optional<Zoologico> findById(Long id);

}
