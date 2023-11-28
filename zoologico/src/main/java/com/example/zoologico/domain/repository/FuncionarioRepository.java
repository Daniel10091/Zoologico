package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.zoologico.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
  
  Optional<Funcionario> findById(Long id);

  @Query(value = "SELECT * FROM funcionario WHERE nu_cpf = ?1", nativeQuery = true)
  Optional<Funcionario> findByNuCpf(String nuCpf);

}
