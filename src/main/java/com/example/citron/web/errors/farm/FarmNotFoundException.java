package com.example.citron.web.errors.farm;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException(String id) {
        super("Farm not found with id: " + id);
    }
}
