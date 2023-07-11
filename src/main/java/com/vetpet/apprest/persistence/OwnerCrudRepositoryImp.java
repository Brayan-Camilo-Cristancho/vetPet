package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.OwnerDtoRepository;
import com.vetpet.apprest.mapper.IownerMapper;
import com.vetpet.apprest.persistence.entity.OwnerEntity;
import com.vetpet.apprest.persistence.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OwnerCrudRepositoryImp extends CrudRepository<OwnerDto> implements OwnerDtoRepository {
    private final IownerMapper iownerMapper;
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerCrudRepositoryImp(IownerMapper iownerMapper, OwnerRepository ownerRepository) {
        this.iownerMapper = iownerMapper;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<OwnerDto> getAll() {
        return iownerMapper.toOwnersDto(ownerRepository.findAll());
    }

    @Override
    public void save(OwnerDto ownerDto) {
        OwnerEntity ownerEntity = iownerMapper.toOwnerEntity(ownerDto);
        ownerEntity.setStatus(true);
        ownerRepository.save(ownerEntity);
    }

    @Override
    public void update(OwnerDto ownerDto) {
        OwnerEntity ownerEntity = iownerMapper.toOwnerEntity(ownerDto);
        ownerRepository.findByEmailAndIdentification(ownerEntity.getEmail(), ownerEntity.getIdentification()).ifPresent(
                ownerEntity1 -> {
                    ownerEntity.setOwnerId(ownerEntity1.getOwnerId());
                    ownerEntity.setCreatedAt(ownerEntity1.getCreatedAt());
                    ownerEntity.setStatus(ownerEntity1.getStatus());
                    ownerRepository.save(ownerEntity);
                }
        );

    }

    @Override
    public void delete(String identification) {
        ownerRepository.findByIdentification(identification).ifPresent(ownerEntity ->
                ownerRepository.deleteById(ownerEntity.getOwnerId())
        );
    }

    @Override
    public Optional<OwnerDto> findByIdentification(String ownerId) {
        return ownerRepository.findByIdentification(ownerId).map(iownerMapper::toOwnerDto);
    }

    @Override
    public Optional<OwnerDto> findByEmail(String email) {
        return ownerRepository.findByEmail(email).map(iownerMapper::toOwnerDto);
    }
}
