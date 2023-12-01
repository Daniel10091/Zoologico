package com.example.zoologico.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zoologico.domain.dto.EnderecoDTO;
import com.example.zoologico.domain.dto.FornecedorDTO;
import com.example.zoologico.domain.mapper.EnderecoMapper;
import com.example.zoologico.domain.mapper.FornecedorMapper;
import com.example.zoologico.domain.service.FornecedorService;

@RestController
@RequestMapping(value = "/api/v1")
public class FornecedorController {
  
  private final FornecedorService fornecedorService;

  private final FornecedorMapper fornecedorMapper;
  private final EnderecoMapper enderecoMapper;

  public FornecedorController(
    FornecedorService fornecedorService, 
    FornecedorMapper fornecedorMapper,
    EnderecoMapper enderecoMapper
  ) {
    this.fornecedorService = fornecedorService;
    this.fornecedorMapper = fornecedorMapper;
    this.enderecoMapper = enderecoMapper;
  }

  // Get all Fornecedores
  @GetMapping(value = "/fornecedor")
  public ResponseEntity<List<FornecedorDTO>> getFornecedores() {
    var result = fornecedorService.getAllFornecedores().stream().collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a Fornecedor by id
  @GetMapping(value = "/fornecedor/{id}")
  public ResponseEntity<FornecedorDTO> findFornecedor(@PathVariable(value = "id") Long id) {
    var result = fornecedorService.findFornecedorById(id);
    return ResponseEntity.ok(fornecedorMapper.toDto(result));
  }

  // Register a new Fornecedor
  @PostMapping(value = "/fornecedor")
  public ResponseEntity<FornecedorDTO> registerFornecedor(@RequestBody FornecedorDTO fornecedor) {
    EnderecoDTO address = new EnderecoDTO(fornecedor.getEnderecoCode(), fornecedor.getPais(), fornecedor.getEstado(), fornecedor.getCidade(), fornecedor.getLogradouro(), fornecedor.getComplemento());
    var result = fornecedorService.registerFornecedor(fornecedorMapper.toEntity(fornecedor), enderecoMapper.toEntity(address));
    return ResponseEntity.ok(fornecedorMapper.toDto(result));
  }

  // Update a Fornecedor by id
  @PutMapping(value = "/fornecedor/{id}")
  public ResponseEntity<FornecedorDTO> updateFornecedor(@PathVariable(value = "id") Long id, @RequestBody FornecedorDTO fornecedor) {
    EnderecoDTO address = new EnderecoDTO(fornecedor.getEnderecoCode(), fornecedor.getPais(), fornecedor.getEstado(), fornecedor.getCidade(), fornecedor.getLogradouro(), fornecedor.getComplemento());
    var result = fornecedorService.updateFornecedor(id, fornecedorMapper.toEntity(fornecedor), enderecoMapper.toEntity(address));
    return ResponseEntity.ok(fornecedorMapper.toDto(result));
  }

  // Delete a Fornecedor by id
  @DeleteMapping(value = "/fornecedor/{id}")
  public ResponseEntity<?> deleteFornecedor(@PathVariable(value = "id") Long id) throws Exception {
    fornecedorService.deleteFornecedorById(id);
    return ResponseEntity.ok().build();
  }

}
