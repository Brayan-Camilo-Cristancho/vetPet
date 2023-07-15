package com.vetpet.apprest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ToDoExceptions extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

}
