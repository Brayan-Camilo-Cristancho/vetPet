package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.DoctorEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DoctorRepository extends ListCrudRepository<DoctorEntity, Long> {

    List<DoctorEntity> findAll();
}
