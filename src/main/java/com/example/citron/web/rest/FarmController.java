package com.example.citron.web.rest;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Field;
import com.example.citron.service.FarmService;
import com.example.citron.service.dto.FarmDTO;
import com.example.citron.web.vm.FarmVM;
import com.example.citron.web.vm.mapper.FarmMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    public FarmController(FarmService farmService, FarmMapper farmMapper) {
        this.farmService = farmService;
        this.farmMapper = farmMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FarmResponse> save(@Valid @RequestBody FarmVM farmVM) {
        Farm farm = farmMapper.toEntity(farmVM);
        Farm savedFarm = farmService.save(farm);
        List<Field> fields = savedFarm.getFields();
        FarmResponse response = new FarmResponse(savedFarm.getId(), fields);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public Farm createFarm(@RequestBody @Valid FarmDTO farmDTO) {
        Farm farm = new Farm();
        farm.setName(farmDTO.getName());
        farm.setLocation(farmDTO.getLocation());
        farm.setTotalArea(farmDTO.getTotalArea());
        farm.setCreationDate(farmDTO.getCreationDate());

        if (farmDTO.getFields() != null) {
            List<Field> fields = farmDTO.getFields().stream().map(listField -> {
                Field field = new Field();
                field.setArea(listField.getArea());
                return field;
            }).collect(Collectors.toList());

            farm.setFields(fields);
        }

        return farmService.save(farm);
    }

    public static class FarmResponse {
        private UUID id;
        private List<Field> fields;

        public FarmResponse(UUID id, List<Field> fields) {
            this.id = id;
            this.fields = fields;
        }

        public UUID getId() {
            return id;
        }

        public List<Field> getFields() {
            return fields;
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FarmDTO> findById(@PathVariable UUID id) {
        Farm farm = farmService.findById(id.toString());
        FarmDTO farmDTO = farmMapper.toDTO(farm);
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FarmDTO> update(@PathVariable UUID id, @RequestBody FarmVM farmVM) {
        Farm farm = farmMapper.toEntity(farmVM);
        Farm updatedFarm = farmService.update(id, farm);
        FarmDTO farmDTO = farmMapper.toDTO(updatedFarm);
        return ResponseEntity.ok(farmDTO);
    }
}