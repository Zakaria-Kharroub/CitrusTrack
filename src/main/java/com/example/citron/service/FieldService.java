package com.example.citron.service;

import com.example.citron.domaine.Field;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface FieldService {
    Field save(Field field);
    Field findById(String id);
    Field update(UUID id , Field field);
    void deleteById(UUID id);
}