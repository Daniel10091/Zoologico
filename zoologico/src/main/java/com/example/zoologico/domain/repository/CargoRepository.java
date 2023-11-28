package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zoologico.domain.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

  Optional<Cargo> findById(Long id);
  
}
