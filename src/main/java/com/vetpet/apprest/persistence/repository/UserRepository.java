package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {
    List<UserEntity> findAllBySex(Character sex);

    Optional<UserEntity> findByEmailOrIdentification(String email, String identification);

    List<UserEntity> findByPhone(String phone);
}
