package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.HarvestDetail;
import com.example.citron.service.dto.HarvestDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
    @Mapping(target = "harvest.id", source = "harvestId")
    @Mapping(target = "tree.id", source = "treeId")
    @Mapping(target = "quantite", source = "quantity")
    HarvestDetail toEntity(HarvestDetailDTO harvestDetailDTO);

    @Mapping(target = "harvestId", source = "harvest.id")
    @Mapping(target = "treeId", source = "tree.id")
    @Mapping(target = "quantity", source = "quantite")
    HarvestDetailDTO toDTO(HarvestDetail harvestDetail);
}