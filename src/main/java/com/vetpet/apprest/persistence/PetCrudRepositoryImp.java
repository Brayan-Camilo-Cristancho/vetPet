package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.mapper.IpetMapper;
import com.vetpet.apprest.persistence.entity.PetEntity;
import com.vetpet.apprest.persistence.repository.OwnerRepository;
import com.vetpet.apprest.persistence.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public class PetCrudRepositoryImp extends CrudRepository<PetDto> implements PetDtoRepository {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final IpetMapper ipetMapper;


    @Autowired
    public PetCrudRepositoryImp(PetRepository petRepository, OwnerRepository ownerRepository, IpetMapper ipetMapper) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
        this.ipetMapper = ipetMapper;
    }

    @Override
    @Transactional
    public List<PetDto> findBySpecies(String specie) {
        List<PetEntity> pets = petRepository.findBySpeciesOrderByName(specie);
        return ipetMapper.toPetsDto(pets);
    }

    @Override
    @Transactional
    public List<PetDto> findByOwner(String email, String iden) {
        return ipetMapper.toPetsDto(petRepository.findByOwnerEntityEmailAndOwnerEntityIdentification(email, iden));
    }

    @Override
    @Transactional
    public Optional<PetDto> findByChip(String chip) {
        return petRepository.findByIdChip(chip).map(ipetMapper::toPetDto);
    }

    @Override
    @Transactional
    public List<PetDto> getAll() {
        return ipetMapper.toPetsDto(petRepository.findAll());
    }

    @Override
    public void save(PetDto petDto) {

        String iden = petDto.getOwnerPet().getIdenOwner();
        String email = petDto.getOwnerPet().getEmailOwner();
        PetEntity petEntity = ipetMapper.toPetEntity(petDto);
        petEntity.setStatus(true);
        ownerRepository.findByEmailAndIdentification(email, iden).ifPresentOrElse(ownerEntity -> {
            petEntity.setOwnerEntity(ownerEntity);
            petEntity.setOwnerId(ownerEntity.getOwnerId());
            petRepository.save(petEntity);
        }, () -> {
            throw new RuntimeException("Error message");
        });
    }

    @Override
    @Transactional
    public void update(PetDto petDto) {
        PetEntity petEntity = ipetMapper.toPetEntity(petDto);
        petRepository.findByIdChip(petEntity.getIdChip()).ifPresentOrElse(petEntity1 -> {
            petEntity.setPetId(petEntity1.getPetId());
            petEntity.setCreatedAt(petEntity1.getCreatedAt());
            petEntity.setStatus(petEntity1.getStatus());
            if (petEntity.getOwnerEntity() != null) {
                ownerRepository.findByEmailAndIdentification(petEntity.getOwnerEntity().getEmail(), petEntity.getOwnerEntity().getIdentification()).ifPresentOrElse(ownerEntity -> {
                    petEntity.setOwnerId(ownerEntity.getOwnerId());
                    petEntity.setOwnerEntity(ownerEntity);
                }, () -> {
                    throw new RuntimeException();
                });
            } else {
                petEntity.setOwnerId(petEntity1.getOwnerId());
                petEntity.setOwnerEntity(petEntity1.getOwnerEntity());
            }
            petRepository.save(petEntity);
        }, () -> {
            throw new RuntimeException();
        });
    }

    @Override
    @Transactional
    public void delete(String chipId) {
        if (petRepository.existsByIdChip(chipId)) {
            petRepository.deleteByIdChip(chipId);
        } else {
            throw new RuntimeException();
        }
    }
}
