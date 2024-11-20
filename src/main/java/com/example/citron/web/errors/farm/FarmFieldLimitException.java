package com.example.citron.web.errors.farm;

public class FarmFieldLimitException extends RuntimeException{
    public FarmFieldLimitException(String message){
        super(message);
    }
}
