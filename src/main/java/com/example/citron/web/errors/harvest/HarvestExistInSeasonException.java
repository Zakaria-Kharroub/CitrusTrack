package com.example.citron.web.errors.harvest;

public class HarvestExistInSeasonException extends RuntimeException {
    public HarvestExistInSeasonException(String message) {
        super(message);
    }
}
