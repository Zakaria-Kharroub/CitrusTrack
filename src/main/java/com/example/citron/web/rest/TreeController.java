package com.example.citron.web.rest;

import com.example.citron.domaine.Tree;
import com.example.citron.service.TreeService;
import com.example.citron.service.dto.TreeDTO;
import com.example.citron.web.vm.TreeVM;
import com.example.citron.web.vm.mapper.TreeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tree")
public class TreeController {

    private final TreeService treeService;
    private final TreeMapper treeMapper;

    public TreeController(TreeService treeService, TreeMapper treeMapper) {
        this.treeService = treeService;
        this.treeMapper = treeMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<TreeDTO> save(@RequestBody TreeVM treeVM) {
        Tree tree = treeMapper.toEntity(treeVM);
        Tree savedTree = treeService.save(tree);
        TreeDTO treeDTO = treeMapper.toDTO(savedTree);
        return new ResponseEntity<>(treeDTO, HttpStatus.CREATED);
    }


}
