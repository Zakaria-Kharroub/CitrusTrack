package com.example.citron.web.rest;

import com.example.citron.domaine.Field;
import com.example.citron.service.FieldService;
import com.example.citron.service.dto.FieldDTO;
import com.example.citron.web.vm.FieldVM;
import com.example.citron.web.vm.mapper.FieldMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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


    @GetMapping("/find/{id}")
    public ResponseEntity<FieldDTO> findById(@PathVariable UUID id){
        Field field = fieldService.findById(id.toString());
        FieldDTO fieldDTO = fieldMapper.toDTO(field);
        return new ResponseEntity<>(fieldDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FieldDTO> update(@PathVariable UUID id, @Valid @RequestBody FieldVM fieldVM) {
        Field field = fieldMapper.toEntity(fieldVM);
        Field updatedField = fieldService.update(id, field);
        FieldDTO fieldDTO = fieldMapper.toDTO(updatedField);
        return ResponseEntity.ok(fieldDTO);
    }






}