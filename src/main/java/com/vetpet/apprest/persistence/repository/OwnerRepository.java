package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.OwnerEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


public interface OwnerRepository extends ListCrudRepository<OwnerEntity, Long> {

    List<OwnerEntity> findAllBy();


}
