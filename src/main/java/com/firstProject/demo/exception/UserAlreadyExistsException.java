//OK
package com.firstProject.demo.exception;

public class UserAlreadyExistsException extends RuntimeException{
  public UserAlreadyExistsException(String message){
    super(message);
  }
}
