package com.example.zoologico.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zoologico.domain.dto.ZoologicoDTO;
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
  public List<ZoologicoDTO> getAllZoologicos() {
    List<Zoologico> zoologicos = null;
    List<ZoologicoDTO> zoologicosDTO = new ArrayList<ZoologicoDTO>();

    zoologicos = zoologicoRepository.findAll();

    zoologicos.stream().forEach(zoologico -> {
      ZoologicoDTO zoologicoDTO = new ZoologicoDTO();
      Fornecedor fornecedor = new Fornecedor();
      Endereco endereco = new Endereco();

      zoologicoDTO.setZoologicoCode(zoologico.getId());
      zoologicoDTO.setZoologicoNome(zoologico.getNome());
      zoologicoDTO.setZoologicoCnpj(zoologico.getCnpj());
      zoologicoDTO.setFornecedorCode(zoologico.getFornecedorId());
      zoologicoDTO.setEnderecoCode(zoologico.getEnderecoId());

      fornecedor = fornecedorService.findFornecedorById(zoologico.getFornecedorId());

      zoologicoDTO.setFornecedorCode(fornecedor.getId());
      zoologicoDTO.setFornecedorCnpj(fornecedor.getCnpj());
      zoologicoDTO.setFornecedorRazaoSocial(fornecedor.getRazaoSocial());

      endereco = enderecoService.findEnderecoById(zoologico.getEnderecoId());
      
      zoologicoDTO.setEnderecoCode(endereco.getId());
      zoologicoDTO.setPais(endereco.getPais());
      zoologicoDTO.setEstado(endereco.getEstado());
      zoologicoDTO.setCidade(endereco.getCidade());
      zoologicoDTO.setLogradouro(endereco.getLogradouro());
      zoologicoDTO.setComplemento(endereco.getComplemento());

      // zoologicoDTO.setFornecedor(fornecedor);
      // zoologicoDTO.setEndereco(endereco);

      zoologicosDTO.add(zoologicoDTO);
    });

    if (zoologicos.isEmpty()) 
      throw new EmptyListException("Nenhum zoologico registrado");

    return zoologicosDTO;
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

    if (endereco.getPais() == null && endereco.getEstado() == null && endereco.getCidade() == null && endereco.getLogradouro() == null)
      throw new RequestErrorException("O endereço não pode ser nulo");

    newEndereco = enderecoService.registerEndereco(endereco);
    
    zoologico.setEnderecoId(newEndereco.getId());

    existFornecedor = fornecedorService.findOneFornecedorById(zoologico.getFornecedorId());

    if (existFornecedor == null) 
      throw new EntityNotFoundException("O fornecedor com o id " + zoologico.getFornecedorId() + " não existe");
    
    zoologico.setCnpj(zoologico.getCnpj().replace(".", "").replace("/", "").replace("-", "").trim());

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
  public Zoologico updateZoologico(Long id, Zoologico zoologico, Endereco endereco) {
    Zoologico zoologicoToUpdate = null;
    Endereco enderecoToUpdate = null;
    Zoologico zoologicoReturn = null;

    zoologicoToUpdate = findZoologicoById(id);

    zoologicoToUpdate.setCnpj(zoologico.getCnpj().replace(".", "").replace("/", "").replace("-", "").trim());
    zoologicoToUpdate.setNome(zoologico.getNome());
    zoologicoToUpdate.setFornecedorId(zoologico.getFornecedorId());
    zoologicoToUpdate.setEnderecoId(zoologico.getEnderecoId());
    
    enderecoToUpdate = enderecoService.findEnderecoById(zoologico.getEnderecoId());

    enderecoToUpdate.setPais(endereco.getPais());
    enderecoToUpdate.setEstado(endereco.getEstado());
    enderecoToUpdate.setCidade(endereco.getCidade());
    enderecoToUpdate.setLogradouro(endereco.getLogradouro());
    enderecoToUpdate.setComplemento(endereco.getComplemento());

    enderecoToUpdate = enderecoService.updateEndereco(enderecoToUpdate.getId(), enderecoToUpdate);

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
