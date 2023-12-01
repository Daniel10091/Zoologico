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
import com.example.zoologico.domain.dto.ZoologicoDTO;
import com.example.zoologico.domain.mapper.EnderecoMapper;
import com.example.zoologico.domain.mapper.ZoologicoMapper;
import com.example.zoologico.domain.service.ZoologicoService;

@RestController
@RequestMapping(value = "/api/v1")
public class ZoologicoController {
  
  private final ZoologicoService zoologicoService;
  private final ZoologicoMapper zoologicoMapper;

  private final EnderecoMapper enderecoMapper;

  public ZoologicoController(
    ZoologicoService zoologicoService, 
    ZoologicoMapper zoologicoMapper,  
    EnderecoMapper enderecoMapper
  ) {
    this.zoologicoService = zoologicoService;
    this.zoologicoMapper = zoologicoMapper;
    this.enderecoMapper = enderecoMapper;
  }

  // Get all Zoologicos
  @GetMapping(value = "/zoologico")
  public ResponseEntity<List<ZoologicoDTO>> getZoologicos() {
    var result = zoologicoService.getAllZoologicos().stream().collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a Zoologico by id
  @GetMapping(value = "/zoologico/{id}")
  public ResponseEntity<ZoologicoDTO> findZoologico(@PathVariable(value = "id") Long id) {
    var result = zoologicoService.findZoologicoById(id);
    return ResponseEntity.ok(zoologicoMapper.toDto(result));
  }

  // Register a new Zoologico
  @PostMapping(value = "/zoologico")
  public ResponseEntity<ZoologicoDTO> registerZoologico(@RequestBody ZoologicoDTO zoologico) {
    EnderecoDTO address = new EnderecoDTO(zoologico.getEnderecoCode(), zoologico.getPais(), zoologico.getEstado(), zoologico.getCidade(), zoologico.getLogradouro(), zoologico.getComplemento());
    var result = zoologicoService.registerZoologico(zoologicoMapper.toEntity(zoologico), enderecoMapper.toEntity(address));
    return ResponseEntity.ok(zoologicoMapper.toDto(result));
  }

  // Update a Zoologico by id
  @PutMapping(value = "/zoologico/{id}")
  public ResponseEntity<ZoologicoDTO> updateZoologico(@PathVariable(value = "id") Long id, @RequestBody ZoologicoDTO zoologico) {
    EnderecoDTO address = new EnderecoDTO(zoologico.getEnderecoCode(), zoologico.getPais(), zoologico.getEstado(), zoologico.getCidade(), zoologico.getLogradouro(), zoologico.getComplemento());
    var result = zoologicoService.updateZoologico(id, zoologicoMapper.toEntity(zoologico), enderecoMapper.toEntity(address));
    return ResponseEntity.ok(zoologicoMapper.toDto(result));
  }

  // Delete a Zoologico by id
  @DeleteMapping(value = "/zoologico/{id}")
  public ResponseEntity<?> deleteZoologico(@PathVariable(value = "id") Long id) throws Exception {
    zoologicoService.deleteZoologicoById(id);
    return ResponseEntity.ok().build();
  }

}
