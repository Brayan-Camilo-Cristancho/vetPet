package com.vetpet.apprest.mapper;

import com.vetpet.apprest.domain.dto.SpecialtyDto;
import com.vetpet.apprest.persistence.entity.SpecialtyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IspecialtyMapper {
    @Mapping(target = "nameSpecialty", source = "name")
    @Mapping(target = "descriptionSpecialty", source = "description")
    SpecialtyDto toSpecialtyDto(SpecialtyEntity specialtyEntity);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "specialtyId", ignore = true)
    SpecialtyEntity toSpecialtyEntity(SpecialtyDto specialtyDto);


}
