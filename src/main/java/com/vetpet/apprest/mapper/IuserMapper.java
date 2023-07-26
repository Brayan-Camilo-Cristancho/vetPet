package com.vetpet.apprest.mapper;

import com.vetpet.apprest.domain.dto.UserDto;
import com.vetpet.apprest.persistence.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING,uses = {IownerMapper.class})
public interface IuserMapper {
    @Mapping(target = "username",source = "username")
    @Mapping(target = "password",source = "password")
    UserDto toUserDto(UserEntity userEntity);
    @InheritInverseConfiguration
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "disabled", ignore = true)
    @Mapping(target = "ownerEntity", ignore = true)
    UserEntity toUserEntity(UserDto userDto);
}
