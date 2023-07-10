package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.domain.repository.Repositroy;
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
public class PetRepositoryImp extends Repositroy<PetDto> implements PetDtoRepository {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final IpetMapper ipetMapper;

    @Autowired
    public PetRepositoryImp(PetRepository petRepository, OwnerRepository ownerRepository, IpetMapper ipetMapper) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
        this.ipetMapper = ipetMapper;
    }

    @Override
    public List<PetDto> findBySpecies(String specie) {
        List<PetEntity> pets = petRepository.findBySpeciesOrderByName(specie);
        return ipetMapper.toPetsDto(pets);
    }

    @Override
    public List<PetDto> findByOwner(String email, String iden) {
        return ipetMapper.toPetsDto(petRepository.findByOwnerEntityEmailAndOwnerEntityIdentification(email, iden));
    }

    @Override
    public Optional<PetDto> findByChip(String chip) {
        return petRepository.findByIdChip(chip).map(ipetMapper::toPetDto);
    }

    @Override
    public List<PetDto> getAll() {
        return ipetMapper.toPetsDto(petRepository.findAll());
    }

    @Override
    @Transactional
    public void save(PetDto petDto) {

        String iden = petDto.getOwnerPet().getIdenOwner();
        String email = petDto.getOwnerPet().getEmailOwner();
        if (ownerRepository.existsByEmailAndIdentification(email, iden)) {
            PetEntity petEntity = ipetMapper.toPetEntity(petDto);
            ownerRepository.findByIdentification(iden).map(ownerEntity -> {
                petEntity.setOwnerEntity(ownerEntity);
                petEntity.setOwnerId(ownerEntity.getOwnerId());
                return petEntity;
            }).ifPresent(petRepository::save);
        }
    }

    @Override
    public void update(PetDto petDto) {
        PetEntity petEntity = ipetMapper.toPetEntity(petDto);
        if (petEntity.getOwnerEntity() != null) {
            String idenOwner = petEntity.getOwnerEntity().getIdentification();
            String emailOwner = petEntity.getOwnerEntity().getEmail();
            if (petRepository.existsByIdChip(petEntity.getIdChip()) && ownerRepository.existsByEmailAndIdentification(emailOwner, idenOwner)) {
                petRepository.save(petEntity);
            }
        } else if (petRepository.existsByIdChip(petEntity.getIdChip())) {
            petRepository.save(petEntity);
        }
    }

    @Override
    public void delete(String chipId) {
        if (petRepository.existsByIdChip(chipId)) {
            petRepository.deleteByIdChip(chipId);
        }
    }
}
