package com.vetpet.apprest.domain.repository;

import com.vetpet.apprest.domain.dto.PetDto;

import java.util.List;

public interface PetDtoRepository {

    List<PetDto> getAll();
}
