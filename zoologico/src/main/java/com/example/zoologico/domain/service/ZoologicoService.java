package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Endereco;
import com.example.zoologico.domain.model.Fornecedor;
import com.example.zoologico.domain.model.Zoologico;
import com.example.zoologico.domain.repository.ZoologicoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ZoologicoService {
  
  private final ZoologicoRepository zoologicoRepository;
  
  private final FornecedorService fornecedorService;
  private final EnderecoService enderecoService;

  public ZoologicoService(
    ZoologicoRepository zoologicoRepository, 
    FornecedorService fornecedorService, 
    EnderecoService enderecoService
  ) {
    this.zoologicoRepository = zoologicoRepository;
    this.fornecedorService = fornecedorService;
    this.enderecoService = enderecoService;
  }

  /**
   * Get all Zoologicos
   * 
   * @return {@code List<Zoologico>}
   */
  public List<Zoologico> getAllZoologicos() {
    List<Zoologico> zoologicos = null;

    zoologicos = zoologicoRepository.findAll();

    if (zoologicos.isEmpty()) 
      throw new EntityNotFoundException("Nenhum zoologico encontrado");

    return zoologicos;
  }

  /**
   * Find a Zoologico by {@code id}
   * 
   * @param id
   * @return <b>{@code Zoologico}</b>
   */
  public Zoologico findZoologicoById(Long id) {
    return zoologicoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Zoologico com o id " + id + " não foi encontrado"));
  }

  /**
   * Register a new Zoologico
   * 
   * @param zoologico, fornecedor, endereco
   * @return <b>{@code Zoologico}</b>
   */
  public Zoologico registerZoologico(Zoologico zoologico, Endereco endereco, Fornecedor fornecedor) {
    final Zoologico newZoologico = new Zoologico();
    Zoologico zoologicoReturn = null;
    Endereco newEndereco = null;
    Fornecedor newFornecedor = null;

    newEndereco = enderecoService.registerEndereco(endereco);
    
    newZoologico.setEnderecoId(newEndereco.getId());

    newFornecedor = fornecedorService.registerFornecedor(fornecedor);

    newZoologico.setFornecedorId(newFornecedor.getId());

    try {
      zoologicoReturn = zoologicoRepository.save(newZoologico);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save zoologico: " + e.getMessage());
    }

    return zoologicoReturn;
  }

  /**
   * Update a Zoologico by {@code id}
   * 
   * @param id
   * @param zoologico
   * @return <b>{@code Zoologico}</b>
   */
  public Zoologico updateZoologico(Long id, Zoologico zoologico) {
    Zoologico zoologicoToUpdate = null;
    Zoologico zoologicoReturn = null;

    zoologicoToUpdate = findZoologicoById(id);

    zoologicoToUpdate.setCnpj(zoologico.getCnpj());
    zoologicoToUpdate.setNome(zoologico.getNome());
    zoologicoToUpdate.setFornecedorId(zoologico.getFornecedorId());
    zoologicoToUpdate.setEnderecoId(zoologico.getEnderecoId());

    try {
      zoologicoReturn = zoologicoRepository.save(zoologicoToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update zoologico: " + e.getMessage());
    }

    return zoologicoReturn;
  }

  /**
   * Delete a Zoologico by {@code id}
   * 
   * @param id
   * @return <b>{@code Zoologico}</b>
   */
  public void deleteZoologicoById(Long id) {
    Zoologico zoologicoToDelete = null;

    zoologicoToDelete = findZoologicoById(id);

    try {
      zoologicoRepository.deleteById(zoologicoToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete zoologico: " + e.getMessage());
    }
  }

}
