package com.example.citron.service.impl;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.Sales;
import com.example.citron.repository.SalesRepository;
import com.example.citron.service.HarvestService;
import com.example.citron.service.SalesService;
import com.example.citron.web.errors.harvest.HarvestNotFoundException;
import com.example.citron.web.errors.sales.SalesQteExceedsHarvestQteException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final HarvestService harvestService;

    public SalesServiceImpl(SalesRepository salesRepository, HarvestService harvestService) {
        this.salesRepository = salesRepository;
        this.harvestService = harvestService;
    }

    @Override
    public Sales save(Sales sales) {
        Optional<Harvest> harvest = harvestService.findById(sales.getHarvest().getId());
        if (harvest.isEmpty()) {
            throw new HarvestNotFoundException("Harvest not found");
        }

        Harvest existingHarvest = harvest.get();
        if (sales.getQuantity() > existingHarvest.getTotalQuantity()) {
            throw new SalesQteExceedsHarvestQteException("sales qte exceeds harvest total quantity");
        }

        existingHarvest.setTotalQuantity(existingHarvest.getTotalQuantity() - sales.getQuantity());
        harvestService.save(existingHarvest);

        sales.setHarvest(existingHarvest);
        return salesRepository.save(sales);
    }
}