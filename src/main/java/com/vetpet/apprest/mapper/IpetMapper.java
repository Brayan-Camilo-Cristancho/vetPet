package com.vetpet.apprest.mapper;

import com.vetpet.apprest.persistence.entity.PetEntity;
import com.vetpet.apprest.domain.dto.PetDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IownerMapper.class})
public interface IpetMapper {

    @Mapping(target = "speciesPet", source = "species")
    @Mapping(target = "ownerPet", source = "ownerEntity")
    @Mapping(target = "hasChipPet", source = "hasChip")
    @Mapping(target = "breedPet", source = "breed")
    @Mapping(target = "birthDatePet", source = "birthDate")
    @Mapping(target = "namePet", source = "name")
    @Mapping(target = "chipIdPet", ignore = true)
    PetDto toPetDto(PetEntity petEntity);

    List<PetDto> toPetsDto(List<PetEntity> pets);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "idChip", source = "chipIdPet")
    PetEntity toPetEntity(PetDto petDto);
}
