package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.HarvestDetail;
import com.example.citron.service.dto.HarvestDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
    @Mapping(target = "treeId", source = "tree.id")
    @Mapping(target = "harvestId", source = "harvest.id")
    HarvestDetailDTO toDTO(HarvestDetail harvestDetail);
}
