package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Farm;
import com.example.citron.service.dto.FarmDTO;
import com.example.citron.web.vm.FarmVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FieldMapper.class})
public interface FarmMapper {
    @Mapping(target = "fields", source = "fields")
    Farm toEntity(FarmVM farmVM);

    FarmDTO toDTO(Farm farm);
}