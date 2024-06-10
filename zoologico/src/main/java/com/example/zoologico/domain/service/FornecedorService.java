package com.example.zoologico.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.dto.FornecedorDTO;
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
  public List<FornecedorDTO> getAllFornecedores() {
    List<Fornecedor> fornecedores = null;
    List<FornecedorDTO> fornecedoresDTO = new ArrayList<FornecedorDTO>();

    fornecedores = fornecedorRepository.findAll();

    fornecedores.stream().forEach(fornecedor -> {
      FornecedorDTO fornecedorDTO = new FornecedorDTO();
      Endereco endereco = new Endereco();

      fornecedorDTO.setFornecedorCode(fornecedor.getId());
      fornecedorDTO.setFornecedorCnpj(fornecedor.getCnpj());
      fornecedorDTO.setFornecedorRazaoSocial(fornecedor.getRazaoSocial());
      fornecedorDTO.setEnderecoCode(fornecedor.getEnderecoId());

      endereco = enderecoService.findEnderecoById(fornecedor.getEnderecoId());

      fornecedorDTO.setPais(endereco.getPais());
      fornecedorDTO.setEstado(endereco.getEstado());
      fornecedorDTO.setCidade(endereco.getCidade());
      fornecedorDTO.setLogradouro(endereco.getLogradouro());
      fornecedorDTO.setComplemento(endereco.getComplemento());

      fornecedoresDTO.add(fornecedorDTO);
    });

    if (fornecedores.isEmpty()) 
      throw new EmptyListException("Nenhum fornecedor registrado");

    return fornecedoresDTO;
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
   * Find a Fornecedor by {@code cnpj}
   * 
   * @param cnpj
   * @return <b>{@code Fornecedor}</b>
   */
  public Fornecedor findFornecedorByCnpj(String cnpj) {
    return fornecedorRepository.findFornecedorByCnpj(cnpj)
        .orElseThrow(() -> new EntityNotFoundException("Fornecedor com o cnpj " + cnpj + " não foi encontrado"));
  }

  /**
   * Register a new Fornecedor
   * 
   * @param fornecedor
   * @return <b>{@code Fornecedor}</b>
   */
  public Fornecedor registerFornecedor(Fornecedor fornecedor, Endereco endereco) {
    Endereco newEndereco = null;
    Fornecedor existFornecedor = null;

    if (endereco.getPais() == null && endereco.getEstado() == null && endereco.getCidade() == null && endereco.getLogradouro() == null)
      throw new RequestErrorException("O endereço não pode ser nulo");

    newEndereco = enderecoService.registerEndereco(endereco);
    
    fornecedor.setEnderecoId(newEndereco.getId());

    //existFornecedor = findFornecedorByCnpj(fornecedor.getCnpj());

    //if (existFornecedor != null) 
      //throw new RequestErrorException("O fornecedor com o cnpj " + fornecedor.getCnpj() + " já existe");
    
    fornecedor.setCnpj(fornecedor.getCnpj().replaceAll(".", "").replaceAll("/", "").replaceAll("-", "").trim());
    
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
  public Fornecedor updateFornecedor(Long id, Fornecedor fornecedor, Endereco endereco) {
    Fornecedor fornecedorToUpdate = null;
    Endereco enderecoToUpdate = null;

    fornecedorToUpdate = findFornecedorById(id);

    fornecedorToUpdate.setCnpj(fornecedor.getCnpj());
    fornecedorToUpdate.setRazaoSocial(fornecedor.getRazaoSocial());

    enderecoToUpdate = enderecoService.findEnderecoById(fornecedor.getEnderecoId());

    enderecoToUpdate.setPais(endereco.getPais());
    enderecoToUpdate.setEstado(endereco.getEstado());
    enderecoToUpdate.setCidade(endereco.getCidade());
    enderecoToUpdate.setLogradouro(endereco.getLogradouro());
    enderecoToUpdate.setComplemento(endereco.getComplemento());

    enderecoToUpdate = enderecoService.updateEndereco(endereco.getId(), endereco);

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
