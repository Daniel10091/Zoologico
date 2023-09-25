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

import com.example.zoologico.domain.dto.FuncionarioDTO;
import com.example.zoologico.domain.mapper.FuncionarioMapper;
import com.example.zoologico.domain.service.FuncionarioService;

@RestController
@RequestMapping(value = "/api/v1")
public class FuncionarioController {
  
  private final FuncionarioService funcionarioService;
  private final FuncionarioMapper funcionarioMapper;

  public FuncionarioController(
    FuncionarioService funcionarioService, 
    FuncionarioMapper funcionarioMapper
  ) {
    this.funcionarioService = funcionarioService;
    this.funcionarioMapper = funcionarioMapper;
  }

  // Get all Funcionarioes
  @GetMapping(value = "/funcionario")
  public ResponseEntity<List<FuncionarioDTO>> getFuncionarios() {
    var result = funcionarioService.getAllFuncionarios().stream().map(funcionarioMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a Funcionario by id
  @GetMapping(value = "/funcionario/{id}")
  public ResponseEntity<FuncionarioDTO> findFuncionario(@PathVariable(value = "id") Long id) {
    var result = funcionarioService.findFuncionarioById(id);
    return ResponseEntity.ok(funcionarioMapper.toDto(result));
  }

  // Register a new Funcionario
  @PostMapping(value = "/funcionario")
  public ResponseEntity<FuncionarioDTO> registerFuncionario(@RequestBody FuncionarioDTO funcionario) {
    var result = funcionarioService.registerFuncionario(funcionarioMapper.toEntity(funcionario));
    return ResponseEntity.ok(funcionarioMapper.toDto(result));
  }

  // Update a Funcionario by id
  @PutMapping(value = "/funcionario/{id}")
  public ResponseEntity<FuncionarioDTO> updateFuncionario(@PathVariable(value = "id") Long id, @RequestBody FuncionarioDTO funcionario) {
    var result = funcionarioService.updateFuncionario(id, funcionarioMapper.toEntity(funcionario));
    return ResponseEntity.ok(funcionarioMapper.toDto(result));
  }

  // Delete a Funcionario by id
  @DeleteMapping(value = "/funcionario/{id}")
  public ResponseEntity<?> deleteFuncionario(@PathVariable(value = "id") Long id) throws Exception {
    funcionarioService.deleteFuncionarioById(id);
    return ResponseEntity.ok().build();
  }

}
