package com.vetpet.apprest.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PetDto {
    private String namePet;
    private String chipIdPet;
    private String speciesPet;
    private String breedPet;
    private OwnerDto ownerPet;
    private Boolean hasChipPet;
    private LocalDate birthDatePet;

}
