package com.example.citron.web.errors;

import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.field.FieldAreaSuperieurCinquanteException;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.field.TotalFieldAreaExceedsFarmAreaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

//    Farm
@ExceptionHandler(FarmNotFoundException.class)
public ResponseEntity<Map<String, String>> handleFarmNotFoundException(FarmNotFoundException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}

//    Field

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleFieldNotFoundException(FieldNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldAreaSuperieurCinquanteException.class)
    public ResponseEntity<String> handleFieldAreaSuperieurCinquanteException(FieldAreaSuperieurCinquanteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TotalFieldAreaExceedsFarmAreaException.class)
    public ResponseEntity<String> handleTotalFieldAreaExceedsFarmAreaException(TotalFieldAreaExceedsFarmAreaException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
