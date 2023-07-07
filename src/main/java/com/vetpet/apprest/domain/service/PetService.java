package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<PetDto> getBySpecies(String specie) {
        return this.petDtoRepository.findBySpecies(specie);
    }

    public PetDto save(PetDto petDto) {
        return this.petDtoRepository.save(petDto);
    }

    public Optional<PetDto> petFindById(Long petId) {
        return this.petDtoRepository.findById(petId);
    }

    public void deletePet(Long petId) {
        this.petDtoRepository.delete(petId);
    }
}
