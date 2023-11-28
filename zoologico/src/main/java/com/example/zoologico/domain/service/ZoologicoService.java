package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EmptyListException;
import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.exception.RequestErrorException;
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
      throw new EmptyListException("Nenhum zoologico registrado");

    return zoologicos;
  }

  // public List<Zoologico> getAllZoologicosByFornecedorId(Long id) {
  //   List<Zoologico> zoologicos = null;

  //   zoologicos = zoologicoRepository.findAllByFornecedorId(id);

  //   if (zoologicos.isEmpty()) 
  //     throw new EmptyListException("Nenhum zoologico registrado");

  //   return zoologicos;
  // }

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
   * Find one Zoologico by {@code id}
   * 
   * @param id
   * @return
   */
  public Zoologico findOneZoologicoById(Long id) {
    Zoologico zoologico = null;

    zoologico = zoologicoRepository.findById(id).orElseThrow(() -> new RequestErrorException("O zoologico com o id " + id + " não existe"));

    return zoologico;
  }

  /**
   * Register a new Zoologico
   * 
   * @param zoologico, fornecedor, endereco
   * @return <b>{@code Zoologico}</b>
   */
  public Zoologico registerZoologico(Zoologico zoologico, Endereco endereco) {
    Endereco newEndereco = null;
    Fornecedor existFornecedor = null;

    newEndereco = enderecoService.registerEndereco(endereco);
    
    zoologico.setEnderecoId(newEndereco.getId());

    existFornecedor = fornecedorService.findOneFornecedorById(zoologico.getFornecedorId());

    if (existFornecedor == null) 
      throw new EntityNotFoundException("O fornecedor com o id " + zoologico.getFornecedorId() + " não existe");

    try {
      zoologico = zoologicoRepository.save(zoologico);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save zoologico: " + e.getMessage());
    }

    return zoologico;
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
