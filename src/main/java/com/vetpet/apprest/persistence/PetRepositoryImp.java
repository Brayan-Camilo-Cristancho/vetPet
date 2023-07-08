package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.mapper.IpetMapper;
import com.vetpet.apprest.persistence.entity.PetEntity;
import com.vetpet.apprest.persistence.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PetRepositoryImp implements PetDtoRepository {
    private final PetRepository petRepository;
    private final IpetMapper ipetMapper;

    @Autowired
    public PetRepositoryImp(PetRepository petRepository, IpetMapper ipetMapper) {
        this.petRepository = petRepository;
        this.ipetMapper = ipetMapper;
    }

    @Override
    public List<PetDto> getAll() {
        List<PetEntity> pets = petRepository.findAll();
        return ipetMapper.toPetsDto(pets);
    }

    @Override
    public List<PetDto> findBySpecies(String specie) {
        List<PetEntity> pets = petRepository.findBySpeciesOrderByName(specie);
        return ipetMapper.toPetsDto(pets);
    }

    @Override
    public PetDto save(PetDto petDto) {
        PetEntity petEntity = ipetMapper.toPetEntity(petDto);
        return ipetMapper.toPetDto(petRepository.save(petEntity));
    }

    @Override
    public Optional<PetDto> findById(Long petId) {
        Optional<PetEntity> petEntity = petRepository.findById(petId);
        return petEntity.map(ipetMapper::toPetDto);
    }

    @Override
    public void delete(Long petId) {
        petRepository.deleteById(petId);
    }

    @Override
    public PetDto update(PetDto petDto) {
        PetEntity petEntity = ipetMapper.toPetEntity(petDto);
        return ipetMapper.toPetDto(petRepository.save(petEntity));
    }

    @Override
    public List<PetDto> findByOwner(String firstName, String lastName, String email) {
        List<PetEntity> petEntities = petRepository.findByOwnerFirstNameAndLastNameAndEmail(firstName,lastName,email);
        return ipetMapper.toPetsDto(petEntities);
    }

}
