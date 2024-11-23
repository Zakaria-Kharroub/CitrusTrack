package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Harvest;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.web.vm.HarvestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {HarvestDetailMapper.class})
public interface HarvestMapper {
    @Mapping(target = "field.id", source = "fieldId")
    @Mapping(target = "quantiteTotale", ignore = true)
    @Mapping(target = "harvestDetails", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Harvest toEntity(HarvestVM harvestVM);

    @Mapping(target = "fieldId", source = "field.id")
    @Mapping(target = "totalQuantity", source = "quantiteTotale")
    @Mapping(target = "harvestDetails", source = "harvestDetails")
    HarvestDTO toDTO(Harvest harvest);
}