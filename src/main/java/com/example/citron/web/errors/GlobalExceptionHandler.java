package com.example.citron.web.errors;

import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.field.FieldAreaSuperieurCinquanteException;
import com.example.citron.web.errors.field.TotalFieldAreaExceedsFarmAreaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    Farm
    @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//    Field
    @ExceptionHandler(FieldAreaSuperieurCinquanteException.class)
    public ResponseEntity<String> handleFieldAreaSuperieurCinquanteException(FieldAreaSuperieurCinquanteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TotalFieldAreaExceedsFarmAreaException.class)
    public ResponseEntity<String> handleTotalFieldAreaExceedsFarmAreaException(TotalFieldAreaExceedsFarmAreaException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
