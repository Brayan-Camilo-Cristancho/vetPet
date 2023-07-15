package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.OwnerEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface OwnerRepository extends ListCrudRepository<OwnerEntity, Long> {
    @Override
    Optional<OwnerEntity> findById(Long id);

    Optional<OwnerEntity> findByIdentification(String iden);

    Optional<OwnerEntity> findByEmail(String email);

    Optional<OwnerEntity> findByEmailAndIdentification(String email, String iden);

}
