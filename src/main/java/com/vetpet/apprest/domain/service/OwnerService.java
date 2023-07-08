package com.vetpet.apprest.domain.service;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.OwnerDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerDtoRepository ownerDtoRepository;

    @Autowired
    public OwnerService(OwnerDtoRepository ownerDtoRepository) {
        this.ownerDtoRepository = ownerDtoRepository;
    }

    public Optional<OwnerDto> ownerGetById(Long ownerId) {
        return ownerDtoRepository.ownerGetById(ownerId);
    }

    public Optional<OwnerDto> ownerGetByEmail(String email) {
        return ownerDtoRepository.ownerGetByEmail(email);
    }

    public OwnerDto saveOwner(OwnerDto ownerDto) {
        return ownerDtoRepository.saveOwner(ownerDto);
    }

    public OwnerDto updateOwner(OwnerDto ownerDto) {
        return ownerDtoRepository.updateOwner(ownerDto);
    }

    public void deleteOwner(String email) {
        ownerDtoRepository.deleteOwner(email);
    }

}
