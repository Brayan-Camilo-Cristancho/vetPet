package com.vetpet.apprest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {
    private String firstNameDoctor;
    private String lastNameDoctor;
    private String phoneDoctor;
    private String emailDoctor;
    private Character sexDoctor;
    private Integer ageDoctor;
    private SpecialtyDto specialtyDto;


}
