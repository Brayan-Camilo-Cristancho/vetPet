package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
