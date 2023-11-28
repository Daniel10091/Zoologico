package com.example.zoologico.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.exception.EmptyListException;
import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.exception.RequestErrorException;
import com.example.zoologico.domain.model.Cargo;
import com.example.zoologico.domain.model.Departamento;
import com.example.zoologico.domain.model.Endereco;
import com.example.zoologico.domain.model.Funcionario;
import com.example.zoologico.domain.repository.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FuncionarioService {
  
  private final FuncionarioRepository funcionarioRepository;
  
  private final EnderecoService enderecoService;
  private final CargoService cargoService;
  private final DepartamentoService departamentoService;
  
  public FuncionarioService(
    FuncionarioRepository funcionarioRepositoty,
    EnderecoService enderecoService,
    CargoService cargoService,
    DepartamentoService departamentoService
  ) {
    this.funcionarioRepository = funcionarioRepositoty;
    this.enderecoService = enderecoService;
    this.cargoService = cargoService;
    this.departamentoService = departamentoService;
  }

  /**
   * Get all Funcionarios
   * 
   * @return {@code List<Funcionario>}
   */
  public List<Funcionario> getAllFuncionarios() {
    List<Funcionario> funcionarios = null;

    funcionarios = funcionarioRepository.findAll();

    if (funcionarios.isEmpty()) 
      throw new EmptyListException("Nenhum funcionário registrado");

    return funcionarios;
  }

  /**
   * Find a Funcionario by {@code id}
   * 
   * @param id
   * @return <b>{@code Funcionario}</b>
   */
  public Funcionario findFuncionarioById(Long id) {
    return funcionarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Funcionário com o id " + id + " não foi encontrado"));
  }

  public Funcionario findFuncionarioByCpf(String cpf) {
    return funcionarioRepository.findByNuCpf(cpf)
        .orElseThrow(() -> new EntityNotFoundException("Funcionário com o CPF " + cpf + " não foi encontrado"));
  }

  /**
   * Register a new Funcionario
   * 
   * @param funcionario
   * @return <b>{@code Funcionario}</b>
   */
  public Funcionario registerFuncionario(Funcionario funcionario, Endereco endereco) {
    Funcionario existFuncionario = null;
    Endereco newEndereco = new Endereco();
    Cargo cargo = null;
    Departamento departamento = null;
    Long departamentoId = null;

    existFuncionario = findFuncionarioByCpf(funcionario.getNuCpf());

    if (existFuncionario != null) 
      throw new RequestErrorException("Já existe um funcionário com o CPF " + funcionario.getNuCpf());

    newEndereco.setPais(endereco.getPais());
    newEndereco.setEstado(endereco.getEstado());
    newEndereco.setCidade(endereco.getCidade());
    newEndereco.setLogradouro(endereco.getLogradouro());
    newEndereco.setComplemento(endereco.getComplemento());

    try {
      newEndereco = enderecoService.registerEndereco(newEndereco);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save endereco: " + e.getMessage());
      throw new RequestErrorException("Erro ao registrar o endereço");
    }

    funcionario.setEnderecoId(newEndereco.getId());

    cargo = this.cargoService.findCargoById(funcionario.getCargoId());

    if (cargo == null) 
      throw new EntityNotFoundException("O cargo com o id " + funcionario.getCargoId() + " não existe");

    // if (cargo.getId() == (long) 1) {
    //   departamentoId = (long) 0;
    // } else if (cargo.getId() == (long) 2) {
    //   departamentoId = (long) 0;
    // } else if (cargo.getId() == (long) 3) {
    //   departamentoId = (long) 2;
    // } else if (cargo.getId() == (long) 4) {
    //   departamentoId = (long) 3;
    // } else if (cargo.getId() == (long) 5) {
    //   departamentoId = (long) 4;
    // } else if (cargo.getId() == (long) 6) {
    //   departamentoId = (long) 0;
    // } else if (cargo.getId() == (long) 7) {
    //   departamentoId = (long) 1;
    // } else if (cargo.getId() == (long) 8) {
    //   departamentoId = (long) 8;
    // } else if (cargo.getId() == (long) 9) {
    //   departamentoId = (long) 9;
    // }

    switch (cargo.getId().toString()) {
      case "1":
        departamentoId = (long) 0;
        break;
      case "2":
        departamentoId = (long) 0;
        break;
      case "3":
        departamentoId = (long) 2;
        break;
      case "4":
        departamentoId = (long) 3;
        break;
      case "5":
        departamentoId = (long) 4;
        break;
      case "6":
        departamentoId = (long) 0;
        break;
      case "7":
        departamentoId = (long) 1;
        break;
      case "8":
        departamentoId = (long) 8;
        break;
      case "9":
        departamentoId = (long) 9;
        break;

      default:
        break;
    }

    departamento = this.departamentoService.findDepartamentoById(departamentoId);

    if (departamento == null) 
      throw new EntityNotFoundException("O departamento com o id " + departamentoId + " não existe");

    funcionario.setDepartamentoId(departamento.getId());
    
    try {
      funcionario = funcionarioRepository.save(funcionario);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to save funcionário: " + e.getMessage());
    }

    return funcionario;
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
      funcionarioReturn = funcionarioRepository.save(funcionarioToUpdate);
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
      funcionarioRepository.deleteById(funcionarioToDelete.getId());
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete endereco: " + e.getMessage());
    }
  }

}
