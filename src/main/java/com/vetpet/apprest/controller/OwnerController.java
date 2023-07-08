package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner-id/{id}")
    public Optional<OwnerDto> ownerGetById(@PathVariable Long id) {
        return ownerService.ownerGetById(id);
    }

    @GetMapping("owner-email")
    public Optional<OwnerDto> ownerGetByEmail(@RequestParam String email) {
        return ownerService.ownerGetByEmail(email);
    }

    @PostMapping("/save-owner")
    public OwnerDto saveOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.saveOwner(ownerDto);
    }

    @PutMapping("/update-owner")
    public OwnerDto updateOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.updateOwner(ownerDto);
    }

    @DeleteMapping("/delete-owner")
    public void deleteOwner(@RequestParam String email) {
        ownerService.deleteOwner(email);
    }
}
