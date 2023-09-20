package com.example.zoologico.domain.exception;

public class EntityAlreadyExistException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  /**
   * @param message
   */
  public EntityAlreadyExistException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public EntityAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

}
