package com.example.citron.web.vm.mapper;

import com.example.citron.domaine.Tree;
import com.example.citron.service.dto.TreeDTO;
import com.example.citron.web.vm.TreeVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    @Mapping(target = "field.id", source = "fieldId")
    Tree toEntity(TreeVM treeVM);

    @Mapping(target = "fieldId", source = "field.id")
    TreeDTO toDTO(Tree tree);
}