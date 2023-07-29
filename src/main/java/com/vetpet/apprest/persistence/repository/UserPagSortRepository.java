package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.UserEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UserPagSortRepository extends ListPagingAndSortingRepository<UserEntity, Long> {

}
