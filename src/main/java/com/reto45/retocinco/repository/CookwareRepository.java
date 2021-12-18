package com.reto45.retocinco.repository;

import com.reto45.retocinco.model.Cookware;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.reto45.retocinco.repository.crud.CookwareCrudRepository;

/**
 *
 * @author desaextremo
 */
@Repository
public class CookwareRepository {

    @Autowired
    private CookwareCrudRepository accesoryCrudRepository;

    public List<Cookware> getAll() {
        return accesoryCrudRepository.findAll();
    }

    public Optional<Cookware> getAccesory(String reference) {
        return accesoryCrudRepository.findById(reference);
    }

    public Cookware create(Cookware accesory) {
        return accesoryCrudRepository.save(accesory);
    }

    public void update(Cookware accesory) {
        accesoryCrudRepository.save(accesory);
    }

    public void delete(Cookware accesory) {
        accesoryCrudRepository.delete(accesory);
    }

    public List<Cookware> getByPrice(double price) {
        return accesoryCrudRepository.findByPrice(price);

    }

    public List<Cookware> getByDescriptionContains(String description) {
        //return accesoryCrudRepository.findByDescriptionContains(description);
        return accesoryCrudRepository.findByDescriptionContainingIgnoreCase(description);
        
    }

}
