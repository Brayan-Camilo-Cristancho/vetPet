package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.PetEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PetRepository extends ListCrudRepository<PetEntity, Long> {
}
