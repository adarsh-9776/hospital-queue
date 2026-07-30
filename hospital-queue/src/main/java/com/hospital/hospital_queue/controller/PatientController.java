
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

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {

        this.patientService = patientService;
    }

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {

        return patientService.addPatient(patient);
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable int id) {

        return patientService.getPatientById(id);
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }
}