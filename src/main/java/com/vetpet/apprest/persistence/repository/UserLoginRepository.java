package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.UserLoginEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserLoginRepository extends ListCrudRepository<UserLoginEntity, String> {

}
