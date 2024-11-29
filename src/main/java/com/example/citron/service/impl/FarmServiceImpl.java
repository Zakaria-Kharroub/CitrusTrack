package com.example.citron.service.impl;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Field;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.FarmRepository;
import com.example.citron.repository.FieldRepository;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.repository.TreeRepository;
import com.example.citron.repository.specification.FarmSpecification;
import com.example.citron.service.FarmService;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;
    private final FieldRepository fieldRepository;
    private final TreeRepository treeRepository;
    private final HarvestDetailRepository harvestDetailRepository;


    public FarmServiceImpl(FarmRepository farmRepository, FieldRepository fieldRepository, TreeRepository treeRepository, HarvestDetailRepository harvestDetailRepository) {
        this.farmRepository=farmRepository ;
        this.fieldRepository = fieldRepository;
        this.treeRepository = treeRepository;
        this.harvestDetailRepository = harvestDetailRepository;
    }

    @Override
    public Farm save(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    public Farm findById(String id) {
        return farmRepository.findById(UUID.fromString(id))
                .orElseThrow(()->new FarmNotFoundException("farm not found"));
    }

    @Override
    public Farm update(UUID id, Farm farm) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(()->new FarmNotFoundException("farm not found"));

        existingFarm.setName(farm.getName());
        existingFarm.setLocation(farm.getLocation());
        existingFarm.setTotalArea(farm.getTotalArea());
        return farmRepository.save(existingFarm);
    }

    @Override
    public Optional<Farm> search(Farm farm) {
        return Optional.ofNullable(farmRepository.findOne(FarmSpecification.getFarmByCriteria(farm))
                .orElseThrow(() -> new FarmNotFoundException("farm not found")));
    }



    @Override
    @Transactional
    public void deleteById(UUID id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("farm not found"));

        List<Field> fields = farm.getFields();
        for (Field field : fields) {
            List<Tree> trees = field.getTrees();
            for (Tree tree : trees) {
                harvestDetailRepository.deleteByTreeId(tree.getId());
                treeRepository.delete(tree);
            }
            fieldRepository.delete(field);
        }
        farmRepository.delete(farm);
    }

}
