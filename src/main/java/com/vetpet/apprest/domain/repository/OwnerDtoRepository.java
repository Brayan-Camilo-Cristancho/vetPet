package com.vetpet.apprest.domain.repository;

import com.vetpet.apprest.domain.dto.OwnerDto;

import java.util.Optional;

public interface OwnerDtoRepository {
    Optional<OwnerDto> ownerGetById(Long ownerId);

    Optional<OwnerDto> ownerGetByEmail(String email);

    OwnerDto saveOwner(OwnerDto ownerDto);

    void deleteOwner(String email);

    OwnerDto updateOwner(OwnerDto ownerDto);

}
