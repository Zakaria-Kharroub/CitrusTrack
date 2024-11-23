package com.example.citron.web.errors.harvest;

public class NoTreesAviableToHarvestException extends RuntimeException {
    public NoTreesAviableToHarvestException(String message) {
        super(message);
    }
}
