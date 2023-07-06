package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.repository.PetDtoRepository;
import com.vetpet.apprest.mapper.IpetMapper;
import com.vetpet.apprest.persistence.entity.PetEntity;
import com.vetpet.apprest.persistence.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
