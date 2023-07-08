package com.vetpet.apprest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OwnerDto {
    private Long idOwner;
    private String firstNameOwner;
    private String lastNameOwner;
    private String addressOwner;
    private String phoneOwner;
    private String emailOwner;


}
