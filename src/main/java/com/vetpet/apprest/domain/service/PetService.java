package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.domain.repository.Repositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetDtoRepository petDtoRepository;
    private final Repositroy<PetDto> repositroy;

    @Autowired
    public PetService(PetDtoRepository petDtoRepository, Repositroy<PetDto> repositroy) {
        this.petDtoRepository = petDtoRepository;
        this.repositroy = repositroy;
    }


    public List<PetDto> getAll() {
        return this.repositroy.getAll();
    }

    public List<PetDto> getBySpecies(String specie) {
        return this.petDtoRepository.findBySpecies(specie);
    }

    public void save(PetDto petDto) {
        this.repositroy.save(petDto);
    }

    public Optional<PetDto> getByChip(String chip) {
        return this.petDtoRepository.findByChip(chip);
    }

    public void deletePet(String chip) {
        this.repositroy.delete(chip);
    }

    public void updatePet(PetDto petDto) {
        this.repositroy.update(petDto);
    }

    public List<PetDto> getByOwner(String email, String iden) {
        return this.petDtoRepository.findByOwner(email , iden);
    }
}
