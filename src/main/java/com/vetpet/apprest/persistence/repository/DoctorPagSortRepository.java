package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DoctorPagSortRepository extends ListPagingAndSortingRepository<DoctorEntity, Long> {

    Page<DoctorEntity> findByAgeBetween(Integer in, Integer fin, Pageable pageable);

    Slice<DoctorEntity> findByAgeGreaterThanAndSex(Integer mayor, Character sex, Pageable pageable);
}
