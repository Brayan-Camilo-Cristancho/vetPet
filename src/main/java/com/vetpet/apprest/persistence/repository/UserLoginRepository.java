package com.vetpet.apprest.persistence.repository;


import com.vetpet.apprest.persistence.entity.UserLoginEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserLoginRepository extends ListCrudRepository<UserLoginEntity, String> {
    Optional<UserLoginEntity> findByUsername(String username);

}
