package com.vetpet.apprest.mapper;

import com.vetpet.apprest.persistence.entity.OwnerEntity;
import com.vetpet.apprest.domain.dto.OwnerDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IownerMapper {
    @Mapping(target = "emailOwner", source = "email")
    @Mapping(target = "phoneOwner", source = "phone")
    @Mapping(target = "lastNameOwner", source = "lastName")
    @Mapping(target = "firstNameOwner", source = "firstName")
    @Mapping(target = "addressOwner", source = "address")
    @Mapping(target = "idenOwner", ignore = true)
    OwnerDto toOwnerDto(OwnerEntity ownerEntity);

    List<OwnerDto> toOwnersDto(List<OwnerEntity> ownerEntities);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "identification", source = "idenOwner")
    OwnerEntity toOwnerEntity(OwnerDto ownerDto);
}
