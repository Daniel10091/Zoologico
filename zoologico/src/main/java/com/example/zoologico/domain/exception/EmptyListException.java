package com.example.zoologico.domain.exception;

public class EmptyListException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * @param message
   */
  public EmptyListException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public EmptyListException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
