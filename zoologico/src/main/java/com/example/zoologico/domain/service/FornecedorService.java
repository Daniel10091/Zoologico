package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EmptyListException;
import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.exception.RequestErrorException;
import com.example.zoologico.domain.model.Endereco;
import com.example.zoologico.domain.model.Fornecedor;
import com.example.zoologico.domain.repository.FornecedorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FornecedorService {
  
  private final FornecedorRepository fornecedorRepository;

  private final EnderecoService enderecoService;

  public FornecedorService(
    FornecedorRepository fornecedorRepository, 
    EnderecoService enderecoService) {
    this.fornecedorRepository = fornecedorRepository;
    this.enderecoService = enderecoService;
  }

  /**
   * Get all Fornecedores
   * 
   * @return {@code List<Fornecedor>}
   */
  public List<Fornecedor> getAllFornecedores() {
    List<Fornecedor> fornecedores = null;

    fornecedores = fornecedorRepository.findAll();

    if (fornecedores.isEmpty()) 
      throw new EmptyListException("Nenhum fornecedor registrado");

    return fornecedores;
  }

  /**
   * Find a Fornecedor by {@code id}
   * 
   * @param id
   * @return <b>{@code Fornecedor}</b>
   */
  public Fornecedor findFornecedorById(Long id) {
    return fornecedorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Fornecedor com o id " + id + " não foi encontrado"));
  }

  /**
   * Find one Fornecedor by {@code id}
   * 
   * @param id
   * @return
   */
  public Fornecedor findOneFornecedorById(Long id) {
    Fornecedor fornecedor = null;

    fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new RequestErrorException("O fornecedor com o id " + id + " não existe"));

    return fornecedor;
  }

  /**
   * Register a new Fornecedor
   * 
   * @param fornecedor
   * @return <b>{@code Fornecedor}</b>
   */
  public Fornecedor registerFornecedor(Fornecedor fornecedor) {
    Endereco endereco = null;

    endereco = enderecoService.findOneEnderecoById(fornecedor.getEnderecoId());

    if (endereco == null)
      throw new RequestErrorException("O endereco com o id " + fornecedor.getEnderecoId() + " não existe");
    
    try {
      fornecedor = fornecedorRepository.save(fornecedor);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save fornecedor: " + e.getMessage());
    }

    return fornecedor;
  }

  /**
   * Register a new Fornecedor
   * 
   * @param fornecedor
   * @return <b>{@code Fornecedor}</b>
   */
  public Fornecedor registerFornecedor(String cnpj, String razaoSocial, Long enderecoId) {
    Fornecedor newFornecedor = new Fornecedor();
    Fornecedor fornecedorReturn = null;

    newFornecedor.setCnpj(cnpj);
    newFornecedor.setRazaoSocial(razaoSocial);
    newFornecedor.setEnderecoId(enderecoId);
    
    try {
      fornecedorReturn = fornecedorRepository.save(newFornecedor);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save fornecedor: " + e.getMessage());
    }

    return fornecedorReturn;
  }

  /**
   * Update a Fornecedor by {@code id}
   * 
   * @param id
   * @param fornecedor
   * @return <b>{@code Fornecedor}</b>
   */
  public Fornecedor updateFornecedor(Long id, Fornecedor fornecedor) {
    Fornecedor fornecedorToUpdate = null;
    Endereco endereco = null;

    fornecedorToUpdate = findFornecedorById(id);

    endereco = enderecoService.findEnderecoById(fornecedor.getEnderecoId());

    fornecedorToUpdate.setCnpj(fornecedor.getCnpj());
    fornecedorToUpdate.setRazaoSocial(fornecedor.getRazaoSocial());
    fornecedorToUpdate.setEnderecoId(endereco.getId());

    try {
      fornecedorToUpdate = fornecedorRepository.save(fornecedorToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update fornecedor: " + e.getMessage());
    }

    return fornecedorToUpdate;
  }

  /**
   * Delete a Fornecedor by {@code id}
   * 
   * @param id
   * @return <b>{@code void}</b>
   */
  public void deleteFornecedorById(Long id) {
    Fornecedor fornecedorToDelete = null;

    fornecedorToDelete = findFornecedorById(id);

    try {
      fornecedorRepository.deleteById(fornecedorToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete fornecedor: " + e.getMessage());
    }
  }

}
