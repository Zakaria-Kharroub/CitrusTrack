package com.example.citron.web.errors.sales;

public class SalesQteExceedsHarvestQteException extends RuntimeException{
    public SalesQteExceedsHarvestQteException(String message) {
        super(message);
    }
}
