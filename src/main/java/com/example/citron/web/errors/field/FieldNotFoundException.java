package com.example.citron.web.errors.field;

public class FieldNotFoundException extends RuntimeException{
    public FieldNotFoundException(String id){
        super(id);
    }
}
