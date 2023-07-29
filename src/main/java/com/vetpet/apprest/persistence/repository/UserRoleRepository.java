package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.UserRoleEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends ListCrudRepository<UserRoleEntity, String> {
    void deleteByUsername(String username);

    Optional<UserRoleEntity> findByUsername(String username);
}
