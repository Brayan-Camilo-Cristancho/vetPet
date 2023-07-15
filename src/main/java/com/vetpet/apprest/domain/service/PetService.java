package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.agregates.MicrochipIDGenerator;
import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.exceptions.ToDoExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public ResponseEntity<List<PetDto>> getAll() {
        List<PetDto> pets = this.crudRepository.getAll();

        if (pets.isEmpty()) {
            throw new ToDoExceptions("no pets to show", HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(pets);
    }

    public ResponseEntity<List<PetDto>> getBySpecies(String specie) {
        List<PetDto> petDtos = this.petDtoRepository.findBySpecies(specie);
        if (petDtos.isEmpty()) {
            throw new ToDoExceptions("no pets to show", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(petDtos);
    }

    public ResponseEntity<?> save(PetDto petDto) {
        if (petDto.getChipIdPet() == null) {
            MicrochipIDGenerator microchipIDGenerator = new MicrochipIDGenerator();
            petDto.setChipIdPet(microchipIDGenerator.generateMicrochipID());
        }

        if (Optional.ofNullable(petDto.getOwnerPet())
                .map(owner -> Optional.ofNullable(owner.getEmailOwner()).isEmpty() || Optional.ofNullable(owner.getIdenOwner()).isEmpty())
                .orElse(true)) {
            throw new ToDoExceptions("The pet has not been assigned to an owner, make sure to enter a correct email and identification", HttpStatus.BAD_REQUEST);
        }

        try {
            this.crudRepository.save(petDto);

        } catch (DataIntegrityViolationException e) {
            throw new ToDoExceptions("Verify your data and if you wrote a chip verify that it is correctly written", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new ToDoExceptions("Please make sure your ID and email are correct", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Your pet has been created successfully");

    }

    public ResponseEntity<?> getByChip(String chip) {
        Optional<PetDto> petDto = this.petDtoRepository.findByChip(chip);
        if (petDto.isEmpty()) {
            throw new ToDoExceptions("Your pet was not found", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(petDto);
    }

    public ResponseEntity<?> deletePet(String chip) {
        try {
            this.crudRepository.delete(chip);
        } catch (RuntimeException e) {
            throw new ToDoExceptions("Your pet could not be deleted", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Your pet was successfully deleted");

    }

    public ResponseEntity<?> updatePet(PetDto petDto) {
        try {
            this.crudRepository.update(petDto);
        } catch (Exception e) {
            throw new ToDoExceptions("Your pet could not be updated, please check the datagb", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Your pet's data was updated correctly");

    }

    public ResponseEntity<?> getByOwner(String email, String iden) {
        List<PetDto> petDtos = this.petDtoRepository.findByOwner(email, iden);
        if (petDtos.isEmpty()) {
            throw new ToDoExceptions("No content", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(petDtos);
    }
}
