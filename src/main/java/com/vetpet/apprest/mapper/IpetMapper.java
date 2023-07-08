package com.vetpet.apprest.mapper;

import com.vetpet.apprest.persistence.entity.PetEntity;
import com.vetpet.apprest.domain.dto.PetDto;
import org.mapstruct.*;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IownerMapper.class})
public interface IpetMapper {
    @Mapping(target = "idPet", source = "petId")
    @Mapping(target = "speciesPet", source = "species")
    @Mapping(target = "ownerPet", source = "ownerEntity")
    @Mapping(target = "hasChipPet", source = "hasChip")
    @Mapping(target = "breedPet", source = "breed")
    @Mapping(target = "birthDatePet", source = "birthDate")
    @Mapping(target = "namePet", source = "name")
    @Mapping(target = "ownerIdPet", source = "ownerId")
    PetDto toPetDto(PetEntity petEntity);

    List<PetDto> toPetsDto(List<PetEntity> pets);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PetEntity toPetEntity(PetDto petDto);
}
