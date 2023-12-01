package com.example.zoologico.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.dto.FuncionarioDTO;
import com.example.zoologico.domain.exception.EmptyListException;
import com.example.zoologico.domain.exception.EntityNotFoundException;
import com.example.zoologico.domain.exception.RequestErrorException;
import com.example.zoologico.domain.model.Cargo;
import com.example.zoologico.domain.model.Departamento;
import com.example.zoologico.domain.model.Endereco;
import com.example.zoologico.domain.model.Funcionario;
import com.example.zoologico.domain.model.Zoologico;
import com.example.zoologico.domain.repository.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FuncionarioService {
  
  private final FuncionarioRepository funcionarioRepository;
  
  private final EnderecoService enderecoService;
  private final ZoologicoService zoologicoService;
  private final CargoService cargoService;
  private final DepartamentoService departamentoService;
  
  public FuncionarioService(
    FuncionarioRepository funcionarioRepositoty,
    EnderecoService enderecoService,
    ZoologicoService zoologicoService,
    CargoService cargoService,
    DepartamentoService departamentoService
  ) {
    this.funcionarioRepository = funcionarioRepositoty;
    this.enderecoService = enderecoService;
    this.zoologicoService = zoologicoService;
    this.cargoService = cargoService;
    this.departamentoService = departamentoService;
  }

  /**
   * Get all Funcionarios
   * 
   * @return {@code List<Funcionario>}
   */
  public List<FuncionarioDTO> getAllFuncionarios() {
    List<Funcionario> funcionarios = null;
    List<FuncionarioDTO> funcionariosDTO = new ArrayList<FuncionarioDTO>();

    funcionarios = funcionarioRepository.findAll();

    funcionarios.stream().forEach(funcionario -> {
      FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
      Endereco endereco = null;
      Zoologico zoologico = null;
      Cargo cargo = null;
      Departamento departamento = null;

      funcionarioDTO.setCode(funcionario.getId());
      funcionarioDTO.setNome(funcionario.getNome());
      funcionarioDTO.setSobrenome(funcionario.getSobrenome());
      funcionarioDTO.setNuCpf(funcionario.getNuCpf());

      endereco = enderecoService.findEnderecoById(funcionario.getEnderecoId());

      funcionarioDTO.setEnderecoCode(endereco.getId());
      funcionarioDTO.setPais(endereco.getPais());
      funcionarioDTO.setEstado(endereco.getEstado());
      funcionarioDTO.setCidade(endereco.getCidade());
      funcionarioDTO.setLogradouro(endereco.getLogradouro());
      funcionarioDTO.setComplemento(endereco.getComplemento());
      
      zoologico = zoologicoService.findZoologicoById(funcionario.getZoologicoId());

      funcionarioDTO.setZoologicoCode(funcionario.getZoologicoId());
      funcionarioDTO.setZoologicoCnpj(zoologico.getCnpj());
      funcionarioDTO.setZoologicoNome(zoologico.getNome());

      cargo = cargoService.findCargoById(funcionario.getCargoId());

      funcionarioDTO.setCargoCode(funcionario.getCargoId());
      funcionarioDTO.setCargoTitulo(cargo.getTitulo());
      funcionarioDTO.setCargoDescricao(cargo.getDescricao());
      funcionarioDTO.setSalarioBase(cargo.getSalarioBase());

      departamento = departamentoService.findDepartamentoById(funcionario.getDepartamentoId());

      funcionarioDTO.setDepartamentoCode(funcionario.getDepartamentoId());
      funcionarioDTO.setDepartamentoNome(departamento.getNome());

      funcionariosDTO.add(funcionarioDTO);
    });

    if (funcionarios.isEmpty()) 
      throw new EmptyListException("Nenhum funcionário registrado");

    return funcionariosDTO;
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
    return funcionarioRepository.findByNuCpf(cpf);
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

    funcionario.setNuCpf(funcionario.getNuCpf().replaceAll("[^0-9]", ""));

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
      case "1": // Gerente
        departamentoId = (long) 7;
        break;
      case "2": // Pesquisador
        departamentoId = (long) 1;
        break;
      case "3": // Treinador
        departamentoId = (long) 2;
        break;
      case "4": // Cuidador
        departamentoId = (long) 3;
        break;
      case "5": // Veterinário
        departamentoId = (long) 4;
        break;
      case "6": // Atendente de bilheteria
        departamentoId = (long) 6;
        break;
      case "7": // Guia turístico
        departamentoId = (long) 5;
        break;
      case "8": // Segurança
        departamentoId = (long) 8;
        break;
      case "9": // Zelador
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
  public Funcionario updateFuncionario(Long id, Funcionario funcionario, Endereco endereco) {
    Funcionario funcionarioToUpdate = null;
    Endereco enderecoToUpdate = new Endereco();

    funcionarioToUpdate = findFuncionarioById(id);

    if (funcionarioToUpdate == null) 
      throw new EntityNotFoundException("Funcionário com o id " + id + " não foi encontrado");

    funcionarioToUpdate.setNome(funcionario.getNome());
    funcionarioToUpdate.setSobrenome(funcionario.getSobrenome());
    funcionarioToUpdate.setNuCpf(funcionario.getNuCpf().replaceAll("[^0-9]", ""));

    enderecoToUpdate.setPais(endereco.getPais());
    enderecoToUpdate.setEstado(endereco.getEstado());
    enderecoToUpdate.setCidade(endereco.getCidade());
    enderecoToUpdate.setLogradouro(endereco.getLogradouro());
    enderecoToUpdate.setComplemento(endereco.getComplemento());

    try {
      enderecoToUpdate = enderecoService.updateEndereco(funcionarioToUpdate.getEnderecoId(), enderecoToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update endereco: " + e.getMessage());
    }

    funcionarioToUpdate.setZoologicoId(funcionario.getZoologicoId());
    funcionarioToUpdate.setCargoId(funcionario.getCargoId());
    funcionarioToUpdate.setDepartamentoId(funcionario.getDepartamentoId());

    try {
      funcionarioToUpdate = funcionarioRepository.save(funcionarioToUpdate);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to update funcionário: " + e.getMessage());
    }

    return funcionarioToUpdate;
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
