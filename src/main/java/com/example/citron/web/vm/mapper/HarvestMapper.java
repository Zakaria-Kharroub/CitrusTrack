package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.HarvestDetail;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.service.dto.HarvestDetailDTO;
import com.example.citron.web.vm.HarvestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(target = "fieldId", source = "id")
    HarvestDTO toDTO(Harvest harvest);

    @Mapping(target = "treeId", source = "tree.id")
    HarvestDetailDTO toDTO(HarvestDetail harvestDetail);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "harvestDetails", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Harvest toEntity(HarvestVM request);
}