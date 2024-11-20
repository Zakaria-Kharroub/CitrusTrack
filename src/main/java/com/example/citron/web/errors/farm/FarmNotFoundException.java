package com.example.citron.web.errors.farm;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException(String id) {
        super(id);
    }
}
