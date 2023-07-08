package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.OwnerDtoRepository;
import com.vetpet.apprest.mapper.IownerMapper;
import com.vetpet.apprest.persistence.entity.OwnerEntity;
import com.vetpet.apprest.persistence.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class OwnerRepositoryImp implements OwnerDtoRepository {
    private final IownerMapper iownerMapper;
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerRepositoryImp(IownerMapper iownerMapper, OwnerRepository ownerRepository) {
        this.iownerMapper = iownerMapper;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<OwnerDto> ownerGetById(Long ownerId) {
        Optional<OwnerEntity> ownerEntity = ownerRepository.findById(ownerId);
        return ownerEntity.map(iownerMapper::toOwnerDto);
    }

    @Override
    public Optional<OwnerDto> ownerGetByEmail(String email) {
        Optional<OwnerEntity> ownerEntity = ownerRepository.findByEmail(email);
        return ownerEntity.map(iownerMapper::toOwnerDto);
    }

    @Override
    public OwnerDto saveOwner(OwnerDto ownerDto) {
        OwnerEntity ownerEntity = iownerMapper.toOwnerEntity(ownerDto);

        return iownerMapper.toOwnerDto(ownerRepository.save(ownerEntity));
    }

    @Override
    @Transactional
    public void deleteOwner(String email) {
        ownerRepository.deleteByEmail(email);
    }

    @Override
    public OwnerDto updateOwner(OwnerDto ownerDto) {
        OwnerEntity ownerEntity = iownerMapper.toOwnerEntity(ownerDto);

        return iownerMapper.toOwnerDto(ownerRepository.save(ownerEntity));
    }
}
