package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.DoctorDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.DoctorDtoRepository;
import com.vetpet.apprest.exceptions.ToDoExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorDtoRepository doctorDtoRepository;
    private final CrudRepository<DoctorDto> crudRepository;

    public DoctorService(DoctorDtoRepository doctorDtoRepository, CrudRepository<DoctorDto> crudRepository) {
        this.doctorDtoRepository = doctorDtoRepository;
        this.crudRepository = crudRepository;
    }

    public ResponseEntity<?> getAll() {
        List<DoctorDto> doctorDtos = this.crudRepository.getAll();
        if (doctorDtos.isEmpty()) {
            throw new ToDoExceptions("There is no information to display", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(doctorDtos);
    }

    public ResponseEntity<?> getAllOfPage() {
        Page<DoctorDto> doctorDtos = this.doctorDtoRepository.getAllOfPage();
        if (doctorDtos.isEmpty()) {
            throw new ToDoExceptions("There is no information to display", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(doctorDtos);
    }

    public ResponseEntity<?> getBySexAndAge() {
        Slice<DoctorDto> doctorDtos = this.doctorDtoRepository.getBySexAndAge();
        if (doctorDtos.isEmpty()) {
            throw new ToDoExceptions("There is no information to display", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(doctorDtos);
    }
}
