package com.example.zoologico.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zoologico.domain.dto.EspecieDTO;
import com.example.zoologico.domain.mapper.EspecieMapper;
import com.example.zoologico.domain.service.EspecieService;

@RestController
@RequestMapping(value = "/api/v1")
public class EspecieController {
  
  private final EspecieService especieService;
  private final EspecieMapper especieMapper;

  public EspecieController(
    EspecieService especieService,
    EspecieMapper especieMapper
  ) {
    this.especieService = especieService;
    this.especieMapper = especieMapper;
  }

  @GetMapping(value = "/especie")
  public ResponseEntity<List<EspecieDTO>> getEspecies() {
    var result = especieService.getAllEspecies().stream().map(especieMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  @GetMapping(value = "/especie/{id}")
  public ResponseEntity<EspecieDTO> findEspecie(@PathVariable(value = "id") Long id) {
    var result = especieMapper.toDto(especieService.findEspecieById(id));
    return ResponseEntity.ok(result);
  }

}
