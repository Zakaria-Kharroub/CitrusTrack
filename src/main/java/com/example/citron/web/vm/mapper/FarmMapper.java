package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Farm;
import com.example.citron.service.dto.FarmDTO;
import com.example.citron.web.vm.FarmSearchVM;
import com.example.citron.web.vm.FarmVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmDTO toDTO(Farm farm);
    Farm toEntity(FarmVM farmVM);
    Farm toEntity(FarmSearchVM farmSearchVM);
}