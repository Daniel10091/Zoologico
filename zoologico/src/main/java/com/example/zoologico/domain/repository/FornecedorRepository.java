package com.example.zoologico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.zoologico.domain.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
  
  Optional<Fornecedor> findById(Long id);

  @Query(value = "SELECT * FROM fornecedor WHERE cnpj = ?1", nativeQuery = true)
  Optional<Fornecedor> findFornecedorByCnpj(@Param("cnpj") String cnpj);

}
