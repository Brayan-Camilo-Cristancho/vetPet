package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.OwnerDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<OwnerDto> ownerGetAll() {
        return this.crudRepository.getAll();
    }

    public void save(OwnerDto ownerDto) {
        this.crudRepository.save(ownerDto);
    }

    public void update(OwnerDto ownerDto) {
        this.crudRepository.update(ownerDto);
    }

    public void delete(String iden) {
        crudRepository.delete(iden);
    }

    public Optional<OwnerDto> getById(String id) {
        return this.ownerDtoRepository.findByIdentification(id);
    }

    public Optional<OwnerDto> getByEmail(String email) {
        return this.ownerDtoRepository.findByEmail(email);
    }
}
