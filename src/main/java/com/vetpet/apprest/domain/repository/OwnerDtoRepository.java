package com.vetpet.apprest.domain.repository;

import com.vetpet.apprest.domain.dto.OwnerDto;

import java.util.Optional;

public interface OwnerDtoRepository {
    Optional<OwnerDto> findByIdentification(String ownerId);

    Optional<OwnerDto> findByEmail(String email);

}
