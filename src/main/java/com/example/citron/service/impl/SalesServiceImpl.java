package com.example.citron.service.impl;

import com.example.citron.repository.SalesRepository;
import com.example.citron.service.SalesService;

public class SalesServiceImpl {

    private final SalesRepository salesRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

}
