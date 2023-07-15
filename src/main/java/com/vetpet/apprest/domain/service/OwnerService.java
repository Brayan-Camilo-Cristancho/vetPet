package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.OwnerDtoRepository;
import com.vetpet.apprest.exceptions.ToDoExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class OwnerService {
    private final OwnerDtoRepository ownerDtoRepository;
    private final CrudRepository<OwnerDto> crudRepository;

    @Autowired
    public OwnerService(OwnerDtoRepository ownerDtoRepository, CrudRepository<OwnerDto> crudRepository) {
        this.ownerDtoRepository = ownerDtoRepository;
        this.crudRepository = crudRepository;
    }

    public ResponseEntity<?> ownerGetAll() {
        List<OwnerDto> ownerDtos = this.crudRepository.getAll();
        if (ownerDtos.isEmpty()) {
            throw new ToDoExceptions("No owners to view", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(ownerDtos);
    }

    public ResponseEntity<?> save(OwnerDto ownerDto) {
        try {
            this.crudRepository.save(ownerDto);
        } catch (DataIntegrityViolationException e) {
            throw new ToDoExceptions("Check your data again", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Owner created successfully");
    }

    public ResponseEntity<?> update(OwnerDto ownerDto) {
        try {
            this.crudRepository.update(ownerDto);
        } catch (Exception e) {
            throw new ToDoExceptions("Check your data again", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Data updated successfully");
    }

    public ResponseEntity<?> delete(String iden) {
        try {
            crudRepository.delete(iden);
        } catch (RuntimeException e) {
            throw new ToDoExceptions("Owner not found as it does not exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Owner deleted successfully");
    }

    public ResponseEntity<?> getById(String id) {
        Optional<OwnerDto> ownerDto = this.ownerDtoRepository.findByIdentification(id);
        if (ownerDto.isEmpty()) {
            throw new ToDoExceptions("Searched owner not found", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(ownerDto);
    }

    public ResponseEntity<?> getByEmail(String email) {
        Optional<OwnerDto> ownerDto = this.ownerDtoRepository.findByEmail(email);
        if (ownerDto.isEmpty()) {
            throw new ToDoExceptions("Searched owner not found", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(ownerDto);
    }
}
