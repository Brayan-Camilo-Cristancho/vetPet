package com.vetpet.apprest.domain.repository;


import com.vetpet.apprest.domain.dto.PetDto;

import java.util.List;
import java.util.Optional;

public interface PetDtoRepository {

    List<PetDto> findBySpecies(String specie);

    List<PetDto> findByOwner(String email, String iden);

    Optional<PetDto> findByChip(String chip);
}
