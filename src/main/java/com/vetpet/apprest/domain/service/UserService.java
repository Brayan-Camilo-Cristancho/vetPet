package com.vetpet.apprest.domain.service;


import com.vetpet.apprest.domain.dto.UserDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.UserDtoRepository;
import com.vetpet.apprest.exceptions.InvalidRoleException;
import com.vetpet.apprest.exceptions.ToDoExceptions;
import com.vetpet.apprest.exceptions.UserNotFoundException;
import com.vetpet.apprest.exceptions.ValuesNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final CrudRepository<UserDto> crudRepository;
    private final UserDtoRepository userDtoRepository;

    @Autowired
    public UserService(CrudRepository<UserDto> crudRepository, UserDtoRepository userDtoRepository) {
        this.crudRepository = crudRepository;
        this.userDtoRepository = userDtoRepository;
    }

    public ResponseEntity<?> userGetAll() {
        List<UserDto> userDtos = this.crudRepository.getAll();
        if (userDtos.isEmpty()) {
            throw new ToDoExceptions("No users to view", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(userDtos);
    }

    public ResponseEntity<?> userSave(UserDto userDto) {
        try {
            this.crudRepository.save(userDto, "customer");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ToDoExceptions("Check your data again", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");

    }

    public ResponseEntity<?> userUpdateOther(UserDto userDto) {
        try {
            this.userDtoRepository.updateOther(userDto);

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new ToDoExceptions("Failed to update user, check username", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User updated successfully");
    }

    public ResponseEntity<?> userDelete(String username) {
        try {
            this.crudRepository.delete(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new ToDoExceptions("User not found for username:" + username, HttpStatus.BAD_REQUEST);
        } catch (InvalidRoleException e) {
            e.printStackTrace();
            throw new ToDoExceptions("Cannot delete user with role other than customer", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User deleted successfully");
    }

    public ResponseEntity<?> userGetBySex(Character c) {
        try {
            return ResponseEntity.ok(userDtoRepository.findAllBySex(c));
        } catch (ValuesNotFound e) {
            e.printStackTrace();
            throw new ToDoExceptions("No users to view", HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<?> userGetByEmailOrIdentification(String email, String identification) {
        try {
            return ResponseEntity.ok(this.userDtoRepository.findByEmailOrIdentification(email, identification));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new ToDoExceptions("No data to display", HttpStatus.NO_CONTENT);
        } catch (IncorrectResultSizeDataAccessException e) {
            e.printStackTrace();
            throw new ToDoExceptions("Data inconsistency", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> userGetByPhone(String phone) {
        List<UserDto> userDtos = this.userDtoRepository.findByPhone(phone);
        if (userDtos.isEmpty()) {
            throw new ToDoExceptions("No users to view", HttpStatus.NO_CONTENT);

        }
        return ResponseEntity.ok(userDtos);
    }

    public ResponseEntity<?> userGetAllPage(Integer pag, Integer size) {
        Page<UserDto> userDtos = userDtoRepository.getAllPage(pag, size);
        if (userDtos.isEmpty()) {
            throw new ToDoExceptions("No users to view", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(userDtos);
    }

    public ResponseEntity<?> userUpdate(UserDto user) {
        try {
            this.crudRepository.update(user);

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new ToDoExceptions("User not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User updated successfully");
    }
}
