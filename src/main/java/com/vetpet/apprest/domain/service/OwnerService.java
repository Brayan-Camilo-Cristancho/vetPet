package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.exceptions.ToDoExceptions;
import com.vetpet.apprest.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final CrudRepository<OwnerDto> crudRepository;
    @Autowired
    public OwnerService(CrudRepository<OwnerDto> crudRepository) {
        this.crudRepository = crudRepository;
    }


    public ResponseEntity<?> OwnerGetAll() {
        try {
            return ResponseEntity.ok(crudRepository.getAll());

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new ToDoExceptions("No users to view", HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<?> ownerSave(OwnerDto ownerDto) {
        try {
            this.crudRepository.save(ownerDto,"owner");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ToDoExceptions("Check your data again", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Owner created successfully");
    }

}
