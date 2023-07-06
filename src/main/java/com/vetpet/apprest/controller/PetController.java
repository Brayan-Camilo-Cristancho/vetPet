package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/getall")
    public List<PetDto> getAllPets() {
        return petService.getAll();
    }
}
