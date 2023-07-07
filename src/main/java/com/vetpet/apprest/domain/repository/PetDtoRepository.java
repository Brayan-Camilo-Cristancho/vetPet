package com.vetpet.apprest.domain.repository;

import com.vetpet.apprest.domain.dto.PetDto;

import java.util.List;
import java.util.Optional;

public interface PetDtoRepository {

    List<PetDto> getAll();

    List<PetDto> findBySpecies(String specie);

    PetDto save(PetDto petDto);

    Optional<PetDto> findById(Long petId);

    void delete(Long petId);
}
