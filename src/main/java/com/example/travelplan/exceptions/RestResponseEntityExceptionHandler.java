package com.example.travelplan.exceptions;

import com.example.travelplan.models.ErrorMesage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorMesage> todoNotFoundException(UserAlreadyExistException exception, WebRequest request){
        ErrorMesage errorMessage=new ErrorMesage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMesage> todoNotFoundException(UserNotFoundException exception, WebRequest request){
        ErrorMesage errorMessage=new ErrorMesage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }



}
