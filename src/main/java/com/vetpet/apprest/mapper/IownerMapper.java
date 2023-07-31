package com.vetpet.apprest.mapper;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.persistence.entity.OwnerEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IuserMapper.class})
public interface IownerMapper {
    @Mapping(target = "dataUser", source = "userEntity")
    @Mapping(target = "membership", source = "membership")
    @Mapping(target = "totalSpent", source = "totalSpent")
    OwnerDto toOwnerdto(OwnerEntity ownerEntity);

    List<OwnerDto> toUsersDto(List<OwnerEntity> owners);

    @InheritInverseConfiguration
    @Mapping(target = "petEntities", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    OwnerEntity toOwnerEntity(OwnerDto ownerDto);
}
