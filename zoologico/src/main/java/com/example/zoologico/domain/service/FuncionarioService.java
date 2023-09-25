package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.model.Funcionario;
import com.example.zoologico.domain.repository.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FuncionarioService {
  
  private final FuncionarioRepository funcionarioRepositoty;
  
  public FuncionarioService(FuncionarioRepository funcionarioRepositoty) {
    this.funcionarioRepositoty = funcionarioRepositoty;
  }

  /**
   * Get all Funcionarios
   * 
   * @return {@code List<Funcionario>}
   */
  public List<Funcionario> getAllFuncionarios() {
    List<Funcionario> funcionarios = null;

    funcionarios = funcionarioRepositoty.findAll();

    if (funcionarios.isEmpty()) 
      throw new EntityNotFoundException("Nenhum funcionário encontrado");

    return funcionarios;
  }

  /**
   * Find a Funcionario by {@code id}
   * 
   * @param id
   * @return <b>{@code Funcionario}</b>
   */
  public Funcionario findFuncionarioById(Long id) {
    return funcionarioRepositoty.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Funcionário com o id " + id + " não foi encontrado"));
  }

  /**
   * Register a new Funcionario
   * 
   * @param funcionario
   * @return <b>{@code Funcionario}</b>
   */
  public Funcionario registerFuncionario(Funcionario funcionario) {
    Funcionario newFuncionario = null;
    
    try {
      newFuncionario = funcionarioRepositoty.save(funcionario);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save funcionário: " + e.getMessage());
    }

    return newFuncionario;
  }

  /**
   * Update a Funcionario by {@code id}
   * 
   * @param id
   * @param funcionario
   * @return <b>{@code Funcionario}</b>
   */
  public Funcionario updateFuncionario(Long id, Funcionario funcionario) {
    Funcionario funcionarioToUpdate = null;
    Funcionario funcionarioReturn = null;

    funcionarioToUpdate = findFuncionarioById(id);

    funcionarioToUpdate.setNome(funcionario.getNome());
    funcionarioToUpdate.setSobrenome(funcionario.getSobrenome());
    funcionarioToUpdate.setNuCpf(funcionario.getNuCpf());
    funcionarioToUpdate.setEnderecoId(funcionario.getEnderecoId());
    funcionarioToUpdate.setZoologicoId(funcionario.getZoologicoId());
    funcionarioToUpdate.setCargoId(funcionario.getCargoId());
    funcionarioToUpdate.setDepartamentoId(funcionario.getDepartamentoId());
    funcionarioToUpdate.setNuCpf(funcionario.getNuCpf());

    try {
      funcionarioReturn = funcionarioRepositoty.save(funcionarioToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update funcionário: " + e.getMessage());
    }

    return funcionarioReturn;
  }

  /**
   * Delete a Funcionario by {@code id}
   * 
   * @param id
   * @return <b>{@code Funcionario}</b>
   */
  public void deleteFuncionarioById(Long id) throws Exception {
    Funcionario funcionarioToDelete = null;

    funcionarioToDelete = findFuncionarioById(id);

    try {
      funcionarioRepositoty.deleteById(funcionarioToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete endereco: " + e.getMessage());
    }
  }

}
