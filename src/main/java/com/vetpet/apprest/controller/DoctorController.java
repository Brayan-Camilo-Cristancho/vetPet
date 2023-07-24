package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor-all")
    public ResponseEntity<?> getAllDoctors() {
        return doctorService.getAll();
    }

    @GetMapping("/doctor-all-page")
    public ResponseEntity<?> getAllOfPageDoctors() {
        return doctorService.getAllOfPage();
    }

    @GetMapping("/doctor-all-slice")
    public ResponseEntity<?> getBySexAndAgeDoctors() {
        return doctorService.getBySexAndAge();
    }
}
