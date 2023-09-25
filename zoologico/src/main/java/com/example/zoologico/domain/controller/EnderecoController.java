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
import com.example.zoologico.domain.mapper.EnderecoMapper;
import com.example.zoologico.domain.service.EnderecoService;

@RestController
@RequestMapping(value = "/api/v1")
public class EnderecoController {
  
  private final EnderecoService enderecoService;
  private final EnderecoMapper enderecoMapper;

  public EnderecoController(
    EnderecoService enderecoService, 
    EnderecoMapper enderecoMapper
  ) {
    this.enderecoService = enderecoService;
    this.enderecoMapper = enderecoMapper;
  }

  // Get all Enderecos
  @GetMapping(value = "/endereco")
  public ResponseEntity<List<EnderecoDTO>> getEnderecos() {
    var result = enderecoService.getAllEnderecos().stream().map(enderecoMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a Endereco by id
  @GetMapping(value = "/endereco/{id}")
  public ResponseEntity<EnderecoDTO> findEndereco(@PathVariable(value = "id") Long id) {
    var result = enderecoService.findEnderecoById(id);
    return ResponseEntity.ok(enderecoMapper.toDto(result));
  }

  // Register a new Endereco
  @PostMapping(value = "/endereco")
  public ResponseEntity<EnderecoDTO> registerEndereco(@RequestBody EnderecoDTO endereco) {
    var result = enderecoService.registerEndereco(enderecoMapper.toEntity(endereco));
    return ResponseEntity.ok(enderecoMapper.toDto(result));
  }

  // Update a Endereco by id
  @PutMapping(value = "/endereco/{id}")
  public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable(value = "id") Long id, @RequestBody EnderecoDTO endereco) {
    var result = enderecoService.updateEndereco(id, enderecoMapper.toEntity(endereco));
    return ResponseEntity.ok(enderecoMapper.toDto(result));
  }

  // Delete a Endereco by id
  @DeleteMapping(value = "/endereco/{id}")
  public ResponseEntity<?> deleteEndereco(@PathVariable(value = "id") Long id) throws Exception {
    enderecoService.deleteEnderecoById(id);
    return ResponseEntity.ok().build();
  }

}
