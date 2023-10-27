package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.exception.RequestErrorException;
import com.example.zoologico.domain.model.Endereco;
import com.example.zoologico.domain.repository.EnderecoRepositoty;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnderecoService {
  
  private final EnderecoRepositoty enderecoRepositoty;

  public EnderecoService(EnderecoRepositoty enderecoRepositoty) {
    this.enderecoRepositoty = enderecoRepositoty;
  }

  /**
   * Get all Enderecos
   * 
   * @return {@code List<Endereco>}
   */
  public List<Endereco> getAllEnderecos() {
    List<Endereco> enderecos = null;

    enderecos = enderecoRepositoty.findAll();

    if (enderecos.isEmpty()) 
      throw new EntityNotFoundException("Nenhum endereço encontrado");

    return enderecos;
  }

  /**
   * Find a Endereco by {@code id}
   * 
   * @param id
   * @return <b>{@code Endereco}</b>
   */
  public Endereco findEnderecoById(Long id) {
    return enderecoRepositoty.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Endereco com o id " + id + " não foi encontrado"));
  }

  /**
   * Find one Endereco by {@code id}
   * 
   * @param id
   * @return
   */
  public Endereco findOneEnderecoById(Long id) {
    Endereco endereco = null;

    endereco = enderecoRepositoty.findById(id).orElseThrow(() -> new RequestErrorException("O endereço com o id " + id + " não existe"));

    return endereco;
  }

  /**
   * Register a new Endereco
   * 
   * @param endereco
   * @return <b>{@code Endereco}</b>
   */
  public Endereco registerEndereco(Endereco endereco) {
    Endereco newEndereco = null;
    
    try {
      newEndereco = enderecoRepositoty.save(endereco);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save endereco: " + e.getMessage());
    }

    return newEndereco;
  }

  /**
   * Register a new Endereco
   * 
   * @param pais, estado, cidade, logradouto, complemento
   * @return <b>{@code Endereco}</b>
   */
  public Endereco registerEndereco(String pais, String estado, String cidade, String logradouto, String complemento) {
    Endereco newEndereco = new Endereco();
    Endereco endedrecoReturn = null;

    newEndereco.setPais(pais);
    newEndereco.setEstado(estado);
    newEndereco.setCidade(cidade);
    newEndereco.setLogradouro(logradouto);
    newEndereco.setComplemento(complemento);
    
    try {
      endedrecoReturn = enderecoRepositoty.save(newEndereco);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save endereco: " + e.getMessage());
    }

    return endedrecoReturn;
  }

  /**
   * Update a Endereco by {@code id}
   * 
   * @param id
   * @param endereco
   * @return <b>{@code Endereco}</b>
   */
  public Endereco updateEndereco(Long id, Endereco endereco) {
    Endereco enderecoToUpdate = null;
    Endereco enderecoReturn = null;

    enderecoToUpdate = findEnderecoById(id);

    enderecoToUpdate.setPais(endereco.getPais());
    enderecoToUpdate.setEstado(endereco.getEstado());
    enderecoToUpdate.setCidade(endereco.getCidade());
    enderecoToUpdate.setLogradouro(endereco.getLogradouro());
    enderecoToUpdate.setComplemento(endereco.getComplemento());

    try {
      enderecoReturn = enderecoRepositoty.save(enderecoToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update endereco: " + e.getMessage());
    }

    return enderecoReturn;
  }

  /**
   * Delete a Endereco by {@code id}
   * 
   * @param id
   * @return <b>{@code Endereco}</b>
   */
  public void deleteEnderecoById(Long id) throws Exception {
    Endereco enderecoToDelete = null;

    enderecoToDelete = findEnderecoById(id);

    try {
      enderecoRepositoty.deleteById(enderecoToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete endereco: " + e.getMessage());
    }
  }

}
