package com.vetpet.apprest.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OwnerDto {
    private UserDto dataUser;
    private Double totalSpent;
    private Boolean membership;
}
