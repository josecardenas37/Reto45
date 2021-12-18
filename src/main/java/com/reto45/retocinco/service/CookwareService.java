package com.reto45.retocinco.service;

import com.reto45.retocinco.model.Cookware;
import com.reto45.retocinco.repository.CookwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author desaextremo
 */
@Service
public class CookwareService {

    @Autowired
    private CookwareRepository accesoryRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Cookware> getAll() {
        return accesoryRepository.getAll();
    }

    public Optional<Cookware> getAccesory(String reference) {
        return accesoryRepository.getAccesory(reference);
    }

    public Cookware create(Cookware accesory) {
        if (accesory.getReference() == null) {
            return accesory;
        } else {
            return accesoryRepository.create(accesory);
        }
    }

    public Cookware update(Cookware accesory) {

        if (accesory.getReference() != null) {
            Optional<Cookware> accesoryDb = accesoryRepository.getAccesory(accesory.getReference());
            if (!accesoryDb.isEmpty()) {
                if (accesory.getBrand() != null) {
                    accesoryDb.get().setBrand(accesory.getBrand());
                }

                if (accesory.getCategory() != null) {
                    accesoryDb.get().setCategory(accesory.getCategory());
                }

                if (accesory.getMateriales() != null) {
                    accesoryDb.get().setMateriales(accesory.getMateriales());
                }
                if (accesory.getDimensiones()!= null) {
                    accesoryDb.get().setDimensiones(accesory.getDimensiones());
                }
                if (accesory.getDescription() != null) {
                    accesoryDb.get().setDescription(accesory.getDescription());
                }
                if (accesory.getPrice() != 0.0) {
                    accesoryDb.get().setPrice(accesory.getPrice());
                }
                if (accesory.getQuantity() != 0) {
                    accesoryDb.get().setQuantity(accesory.getQuantity());
                }
                if (accesory.getPhotography() != null) {
                    accesoryDb.get().setPhotography(accesory.getPhotography());
                }
                accesoryDb.get().setAvailability(accesory.isAvailability());
                accesoryRepository.update(accesoryDb.get());
                return accesoryDb.get();
            } else {
                return accesory;
            }
        } else {
            return accesory;
        }
    }

    public boolean delete(String reference) {
        Boolean aBoolean = getAccesory(reference).map(accesory -> {
            accesoryRepository.delete(accesory);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public List<Cookware> getByPrice(double price) {
        return accesoryRepository.getByPrice(price);
    }

    public List<Cookware> getByDescriptionContains(String description) {
        return accesoryRepository.getByDescriptionContains(description);
    }
}
