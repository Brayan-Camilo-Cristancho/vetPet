package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DoctorRepository extends ListCrudRepository<DoctorEntity, Long> {

    List<DoctorEntity> findAll();
}
