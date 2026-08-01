
package com.hospital.hospital_queue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import com.hospital.hospital_queue.model.Patient;
import com.hospital.hospital_queue.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
        name = "Patient Management",
        description = "APIs for managing patients"
)
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {

        this.patientService = patientService;
    }

    @Operation(summary = "Add a new patient")
    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {

        return patientService.addPatient(patient);
    }

    @Operation(summary = "Get all patients")
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


    @Operation(summary = "Get patient by ID")
    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable int id) {

        return patientService.getPatientById(id);
    }

    @GetMapping("/phone/{phone}")
    public List<Patient> getPatientByPhone(@PathVariable String phone) {
        return patientService.getPatientByPhone(phone);
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }



}