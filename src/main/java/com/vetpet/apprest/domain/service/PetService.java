package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.agregates.MicrochipIDGenerator;
import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.domain.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetDtoRepository petDtoRepository;
    private final CrudRepository<PetDto> crudRepository;

    @Autowired
    public PetService(PetDtoRepository petDtoRepository, CrudRepository<PetDto> crudRepository) {
        this.petDtoRepository = petDtoRepository;
        this.crudRepository = crudRepository;
    }


    public List<PetDto> getAll() {
        return this.crudRepository.getAll();
    }

    public List<PetDto> getBySpecies(String specie) {
        return this.petDtoRepository.findBySpecies(specie);
    }

    public void save(PetDto petDto) {
        if (petDto.getChipIdPet() == null) {
            MicrochipIDGenerator microchipIDGenerator = new MicrochipIDGenerator();
            petDto.setChipIdPet(microchipIDGenerator.generateMicrochipID());
        }

        this.crudRepository.save(petDto);
    }

    public Optional<PetDto> getByChip(String chip) {
        return this.petDtoRepository.findByChip(chip);
    }

    public void deletePet(String chip) {
        this.crudRepository.delete(chip);
    }

    public void updatePet(PetDto petDto) {
        this.crudRepository.update(petDto);
    }

    public List<PetDto> getByOwner(String email, String iden) {
        return this.petDtoRepository.findByOwner(email, iden);
    }
}
