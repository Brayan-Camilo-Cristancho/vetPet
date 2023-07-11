package com.vetpet.apprest.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerDto {
    private String firstNameOwner;
    private String lastNameOwner;
    private String addressOwner;
    private String phoneOwner;
    private String emailOwner;
    private String idenOwner;


}
