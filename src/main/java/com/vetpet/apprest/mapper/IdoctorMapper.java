package com.vetpet.apprest.mapper;

import com.vetpet.apprest.domain.dto.DoctorDto;
import com.vetpet.apprest.persistence.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IspecialtyMapper.class})
public interface IdoctorMapper {
    @Mapping(target = "firstNameDoctor", source = "firstName")
    @Mapping(target = "lastNameDoctor", source = "lastName")
    @Mapping(target = "emailDoctor", source = "email")
    @Mapping(target = "phoneDoctor", source = "phone")
    @Mapping(target = "specialtyDto", source = "specialtyEntity")
    @Mapping(target = "ageDoctor", source = "age")
    @Mapping(target = "sexDoctor", source = "sex")
    DoctorDto toDoctorDto(DoctorEntity doctorEntity);

    List<DoctorDto> toDoctorsDto(List<DoctorEntity> doctorEntities);

    default Slice<DoctorDto> toDoctorsSliceDto(Slice<DoctorEntity> doctorEntities) {
        return doctorEntities.map(this::toDoctorDto);
    }

    default Page<DoctorDto> toDoctorsPageDto(Page<DoctorEntity> doctorEntities) {
        return doctorEntities.map(this::toDoctorDto);
    }

    @Mapping(target = "status", ignore = true)
    DoctorEntity toDoctorEntity(DoctorDto doctorDto);
}
