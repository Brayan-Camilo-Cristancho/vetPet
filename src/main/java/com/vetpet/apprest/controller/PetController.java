package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/get-all")
    public List<PetDto> getAllPets() {
        return petService.getAll();
    }

    @GetMapping("/species/{specie}")
    public List<PetDto> getBySpecies(@PathVariable String specie) {
        return petService.getBySpecies(specie);
    }

    @PostMapping("/save-pet")
    public PetDto savePet(@RequestBody PetDto petDto) {
        return petService.save(petDto);
    }

    @GetMapping("pet-id/{id}")
    public Optional<PetDto> getById(@PathVariable Long id) {
        return petService.petFindById(id);
    }

    @DeleteMapping("delete-pet/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }

    @PutMapping("/update-pet")
    public PetDto updatePet(@RequestBody PetDto petDto) {
        return petService.updatePet(petDto);
    }

    @GetMapping("/get-owner")
    public List<PetDto> getByOwner(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        return petService.findByOwner(firstName, lastName, email);
    }
}
