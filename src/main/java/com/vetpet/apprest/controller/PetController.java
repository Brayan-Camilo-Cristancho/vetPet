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
    public void savePet(@RequestBody PetDto petDto) {
        petService.save(petDto);
    }

    @GetMapping("pet-id/{id}")
    public Optional<PetDto> getByChip(@PathVariable String id) {
        return petService.getByChip(id);
    }

    @DeleteMapping("delete-pet")
    public void deletePet(@RequestParam String id) {
        petService.deletePet(id);
    }

    @PatchMapping ("/update-pet")
    public void updatePet(@RequestBody PetDto petDto) {
        petService.updatePet(petDto);
    }

    @GetMapping("/get-owner")
    public List<PetDto> getByOwner(@RequestParam String email, @RequestParam String id) {
        return petService.getByOwner(email, id);
    }
}
