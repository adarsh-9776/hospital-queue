
package com.hospital.hospital_queue.controller;

import com.hospital.hospital_queue.model.Clinic;
import com.hospital.hospital_queue.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public Clinic saveClinic(@RequestBody Clinic clinic) {
        return clinicService.saveClinic(clinic);
    }
}