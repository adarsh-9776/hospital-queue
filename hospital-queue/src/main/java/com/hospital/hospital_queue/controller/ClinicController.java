
package com.hospital.hospital_queue.controller;

import com.hospital.hospital_queue.model.Clinic;
import com.hospital.hospital_queue.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(
        name = "Clinic Management",
        description = "APIs for managing clinics"
)
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Operation(summary = "Add a new clinic")
    @PostMapping
    public Clinic saveClinic(@RequestBody Clinic clinic) {
        return clinicService.saveClinic(clinic);
    }

    @Operation(summary = "Get all clinics")
    @GetMapping
    public List<Clinic> getAllClinics() {
        return clinicService.getAllClinics();
    }


    @Operation(summary = "Update clinic details")
    @PutMapping("/{id}")
    public Clinic updateClinic(@PathVariable int id, @RequestBody Clinic clinic) {
        return clinicService.updateClinic(id, clinic);
    }
}