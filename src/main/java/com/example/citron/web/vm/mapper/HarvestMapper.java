package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Harvest;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.web.vm.HarvestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {HarvestDetailMapper.class})
public interface HarvestMapper {
    @Mapping(target = "farm.id", source = "farmId")
    @Mapping(target = "totalQuantity", ignore = true)
    @Mapping(target = "harvestDetails", ignore = true)
    Harvest toEntity(HarvestVM harvestVM);

    @Mapping(target = "farmId", source = "farm.id")
    @Mapping(target = "harvestDetails", source = "harvestDetails")
    HarvestDTO toDTO(Harvest harvest);
}