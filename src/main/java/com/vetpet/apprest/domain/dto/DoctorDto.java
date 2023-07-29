package com.vetpet.apprest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {
    private UserDto userData;
    private SpecialtyDto specialty;
    private Integer numAppointments;


}
