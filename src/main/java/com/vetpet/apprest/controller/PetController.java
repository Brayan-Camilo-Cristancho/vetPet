package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.PetDto;
import com.vetpet.apprest.domain.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPets() {
        return petService.getAll();
    }

    @GetMapping("/species/{specie}")
    public ResponseEntity<?> getBySpecies(@PathVariable String specie) {
        return petService.getBySpecies(specie);
    }

    @PostMapping("/save-pet")
    public ResponseEntity<?> savePet(@RequestBody PetDto petDto) {
        return petService.save(petDto);
    }

    @GetMapping("pet-id/{id}")
    public ResponseEntity<?> getByChip(@PathVariable String id) {
        return petService.getByChip(id);
    }

    @DeleteMapping("delete-pet")
    public ResponseEntity<?> deletePet(@RequestParam String id) {
        return petService.deletePet(id);
    }

    @PatchMapping("/update-pet")
    public ResponseEntity<?> updatePet(@RequestBody PetDto petDto) {
        return petService.updatePet(petDto);
    }

    @GetMapping("/get-owner")
    public ResponseEntity<?> getByOwner(@RequestParam String email, @RequestParam String id) {
        return petService.getByOwner(email, id);
    }
}
