package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.PetEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;


public interface PetRepository extends ListCrudRepository<PetEntity, Long> {
    List<PetEntity> findBySpeciesOrderByName(String species);
}
