package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Field;
import com.example.citron.service.dto.FieldDTO;
import com.example.citron.web.vm.FieldVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(source = "farmId", target = "farm.id")
    Field toEntity(FieldVM fieldVM);

    @Mapping(source = "farm.id", target = "farmId")
    FieldDTO toDTO(Field field);
}