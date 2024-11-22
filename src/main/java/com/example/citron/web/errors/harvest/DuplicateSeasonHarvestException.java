package com.example.citron.web.errors.harvest;

public class DuplicateSeasonHarvestException extends RuntimeException {
    public DuplicateSeasonHarvestException(String message) {
        super(message);
    }
}