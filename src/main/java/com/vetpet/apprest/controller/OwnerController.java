package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOwner() {
        return ownerService.OwnerGetAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.ownerSave(ownerDto);
    }
}
