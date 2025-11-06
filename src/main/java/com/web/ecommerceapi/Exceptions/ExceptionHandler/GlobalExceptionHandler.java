package com.web.ecommerceapi.Exceptions.ExceptionHandler;

import com.web.ecommerceapi.Exceptions.InvalidProductIdException;
import com.web.ecommerceapi.Exceptions.NewProductNameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<String> handleException (InvalidProductIdException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NewProductNameException.class)
    public ResponseEntity<String> handleProductNameException(NewProductNameException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
