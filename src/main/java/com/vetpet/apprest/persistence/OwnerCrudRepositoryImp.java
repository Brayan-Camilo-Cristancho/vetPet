package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.OwnerDtoRepository;
import com.vetpet.apprest.exceptions.UserNotFoundException;
import com.vetpet.apprest.mapper.IownerMapper;
import com.vetpet.apprest.persistence.entity.OwnerEntity;
import com.vetpet.apprest.persistence.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OwnerCrudRepositoryImp extends CrudRepository<OwnerDto> implements OwnerDtoRepository {
    private final OwnerRepository ownerRepository;
    private final IownerMapper iownerMapper;
    private final UserCrudRepositoryImp userCrudRepositoryImp;

    @Autowired
    public OwnerCrudRepositoryImp(OwnerRepository ownerRepository, IownerMapper iownerMapper, UserCrudRepositoryImp userCrudRepositoryImp) {
        this.ownerRepository = ownerRepository;
        this.iownerMapper = iownerMapper;
        this.userCrudRepositoryImp = userCrudRepositoryImp;
    }

    @Override
    public List<OwnerDto> getAll() {
        List<OwnerDto> ownerDtos = iownerMapper.toUsersDto(ownerRepository.findAll());
        if (ownerDtos.isEmpty()) {
            throw new UserNotFoundException("No users to view");
        }
        return ownerDtos;
    }

    @Override
    @Transactional
    public Long save(OwnerDto dto, String role) {

        Long id = userCrudRepositoryImp.save(dto.getDataUser(), role);
        OwnerEntity ownerEntity = iownerMapper.toOwnerEntity(dto);
        ownerEntity.setOwnerId(id);
        ownerEntity.setUserEntity(null);
        ownerRepository.save(ownerEntity);
        return null;
    }

    @Override
    public void update(OwnerDto entity) {


    }

    @Override
    public void delete(String v) {

    }
}
