package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.OwnerEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface OwnerRepository extends ListCrudRepository<OwnerEntity, Long> {


}
