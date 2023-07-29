package com.vetpet.apprest.mapper;

import com.vetpet.apprest.domain.dto.UserDto;
import com.vetpet.apprest.persistence.entity.UserEntity;
import com.vetpet.apprest.persistence.entity.UserLoginEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IuserMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "identification", source = "identification")
    @Mapping(target = "sex", source = "sex")
    @Mapping(target = "birthDate", source = "birthDate")
    UserDto toUserDto(UserEntity userEntity);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "disabled", source = "disabled")
    @Mapping(target = "locked", source = "locked")
    UserLoginEntity toUserLoginEntity(UserDto userDto);

    List<UserDto> toUsersDto(List<UserEntity> userEntities);

    default Page<UserDto> toUsersPageDto(Page<UserEntity> userEntities) {
        return userEntities.map(this::toUserDto);
    }

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserDto userDto);
}
