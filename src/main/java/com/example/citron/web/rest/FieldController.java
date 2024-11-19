package com.example.citron.web.rest;

import com.example.citron.domaine.Field;
import com.example.citron.service.FieldService;
import com.example.citron.service.dto.FieldDTO;
import com.example.citron.web.vm.FieldVM;
import com.example.citron.web.vm.mapper.FieldMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/field")
public class FieldController {

    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    public FieldController(FieldService fieldService, FieldMapper fieldMapper) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FieldDTO> save(@Valid @RequestBody FieldVM fieldVM) {
        Field field = fieldMapper.toEntity(fieldVM);
        Field savedField = fieldService.save(field);
        FieldDTO fieldDTO = fieldMapper.toDTO(savedField);
        return new ResponseEntity<>(fieldDTO, HttpStatus.CREATED);
    }
}