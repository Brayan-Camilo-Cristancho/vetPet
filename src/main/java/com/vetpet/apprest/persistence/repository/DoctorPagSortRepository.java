package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface DoctorPagSortRepository extends ListPagingAndSortingRepository<DoctorEntity, Long> {


}
