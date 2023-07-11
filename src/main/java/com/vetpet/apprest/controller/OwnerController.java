package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.OwnerDto;
import com.vetpet.apprest.domain.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner-all")
    public List<OwnerDto> getAllOwner() {
        return ownerService.ownerGetAll();
    }

    @PostMapping("/owner-save")
    public void saveOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.save(ownerDto);
    }

    @PatchMapping("/owner-update")
    public void updateOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.update(ownerDto);
    }

    @DeleteMapping("/owner-delete")
    public void deleteOwner(@RequestParam String id) {
        ownerService.delete(id);
    }

    @GetMapping("/owner-id/{id}")
    public Optional<OwnerDto> getById(@PathVariable String id) {
        return ownerService.getById(id);
    }

    @GetMapping("/owner-email/{email}")
    public Optional<OwnerDto> getByEmail(@PathVariable String email) {
        return ownerService.getByEmail(email);
    }
}
