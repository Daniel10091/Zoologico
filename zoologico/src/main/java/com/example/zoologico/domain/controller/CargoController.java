package com.example.zoologico.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zoologico.domain.dto.CargoDTO;
import com.example.zoologico.domain.mapper.CargoMapper;
import com.example.zoologico.domain.service.CargoService;

@RestController
@RequestMapping(value = "/api/v1")
public class CargoController {

  private final CargoService cargoService;
  private final CargoMapper cargoMapper;

  public CargoController(
    CargoService cargoService,
    CargoMapper cargoMapper
  ) {
    this.cargoService = cargoService;
    this.cargoMapper = cargoMapper;
  }

  @GetMapping(value = "/cargo")
  public ResponseEntity<List<CargoDTO>> getCargos() {
    var result = cargoService.getAllCargos().stream().map(cargoMapper::_toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  @GetMapping(value = "/cargo/{id}")
  public ResponseEntity<CargoDTO> findCargo(@PathVariable(value = "id") Long id) {
    var result = cargoMapper._toDto(cargoService.findCargoById(id));
    return ResponseEntity.ok(result);
  }
  
}
