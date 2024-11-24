package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Sales;
import com.example.citron.service.dto.SalesDTO;
import com.example.citron.web.vm.SalesVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalesMapper {

    @Mapping(target = "harvestId", source = "harvest.id")
    SalesDTO toDTO(Sales sales);

    @Mapping(target = "harvest", ignore = true)
    Sales toEntity(SalesVM salesVM);
}