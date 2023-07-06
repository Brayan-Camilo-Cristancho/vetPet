package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetDtoRepository petDtoRepository;

    @Autowired
    public PetService(PetDtoRepository petDtoRepository) {
        this.petDtoRepository = petDtoRepository;
    }


    public List<PetDto> getAll() {
        return this.petDtoRepository.getAll();
    }
}
