package com.example.zoologico.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zoologico.domain.dto.DepartamentoDTO;
import com.example.zoologico.domain.mapper.DepartamentoMapper;
import com.example.zoologico.domain.service.DepartamentoService;

@RestController
@RequestMapping(value = "/api/v1")
public class DepartamentoController {
  
  private final DepartamentoService departamentoService;
  private final DepartamentoMapper departamentoMapper;

  public DepartamentoController(
    DepartamentoService departamentoService,
    DepartamentoMapper departamentoMapper
  ) {
    this.departamentoService = departamentoService;
    this.departamentoMapper = departamentoMapper;
  }

  @GetMapping(value = "/departamento")
  public ResponseEntity<List<DepartamentoDTO>> getDepartamentos() {
    var result = departamentoService.getAllDepartamentos().stream().map(departamentoMapper::_toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  @GetMapping(value = "/departamento/{id}")
  public ResponseEntity<DepartamentoDTO> findDepartamento(@PathVariable(value = "id") Long id) {
    var result = departamentoMapper._toDto(departamentoService.findDepartamentoById(id));
    return ResponseEntity.ok(result);
  }

}
