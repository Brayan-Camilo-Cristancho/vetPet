package com.vetpet.apprest.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    @Email(message = "Incorrect email")
    private String email;
    private String identification;
    private Character sex;
    private Date birthDate;
    private Boolean disabled;
    private Boolean locked;
}
