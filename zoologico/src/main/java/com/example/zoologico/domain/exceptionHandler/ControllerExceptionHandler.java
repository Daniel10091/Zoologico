package com.example.zoologico.domain.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.zoologico.domain.dto.ErrorDto;
import com.example.zoologico.domain.exception.RequestErrorException;
import com.example.zoologico.domain.exception.EmptyListException;
import com.example.zoologico.domain.exception.EntityAlreadyExistException;
import com.example.zoologico.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ EmptyListException.class })
  public ResponseEntity<ErrorDto> handleEmptyList(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(204, "Não foi encontrado nenhum registro", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler({ EntityNotFoundException.class })
  public ResponseEntity<ErrorDto> handleEntityNotFound(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(404, "Não encontrado", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler({ EntityAlreadyExistException.class })
  public ResponseEntity<ErrorDto> handleEntityAlreadyExist(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(409, "Entidade já existe", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler({ RequestErrorException.class })
  public ResponseEntity<ErrorDto> handleRequestError(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(400, "Erro de solicitação", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler({ RuntimeException.class })
  public ResponseEntity<ErrorDto> handleOtherExceptions(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(500, "Erro interno do servidor", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

}
