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

    @GetMapping("/owner-all")
    public ResponseEntity<?> getAllOwner() {
        return ownerService.ownerGetAll();
    }

    @PostMapping("/owner-save")
    public ResponseEntity<?> saveOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.save(ownerDto);
    }

    @PatchMapping("/owner-update")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.update(ownerDto);
    }

    @DeleteMapping("/owner-delete")
    public ResponseEntity<?> deleteOwner(@RequestParam String id) {
        return ownerService.delete(id);
    }

    @GetMapping("/owner-id/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ownerService.getById(id);
    }

    @GetMapping("/owner-email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        return ownerService.getByEmail(email);
    }
}
