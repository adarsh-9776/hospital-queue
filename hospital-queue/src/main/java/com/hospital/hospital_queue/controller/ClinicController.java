
package com.hospital.hospital_queue.controller;

import com.hospital.hospital_queue.model.Clinic;
import com.hospital.hospital_queue.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public Clinic saveClinic(@RequestBody Clinic clinic) {
        return clinicService.saveClinic(clinic);
    }

    @GetMapping
    public List<Clinic> getAllClinics() {
        return clinicService.getAllClinics();
    }

    @PutMapping("/{id}")
    public Clinic updateClinic(@PathVariable int id, @RequestBody Clinic clinic) {
        return clinicService.updateClinic(id, clinic);
    }
}