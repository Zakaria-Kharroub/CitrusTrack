package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Harvest;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.web.vm.HarvestVM;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    Harvest toEntity(HarvestVM harvestVM);
    HarvestDTO toDTO(Harvest harvest);
    HarvestVM toVM(Harvest harvest);
    List<HarvestVM> toVMList(List<Harvest> harvests);
}