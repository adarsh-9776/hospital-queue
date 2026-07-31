
package com.hospital.hospital_queue.service;

import com.hospital.hospital_queue.model.Patient;
import com.hospital.hospital_queue.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {

        List<Patient> existingPatients = patientRepository.findByPhone(patient.getPhone());

        if (!existingPatients.isEmpty()) {
            return existingPatients.get(0);
        }

        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id).get();
    }
    public Patient updatePatient(int id, Patient patient) {

        Patient existingPatient = patientRepository.findById(id).get();

        existingPatient.setName(patient.getName());
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setAddress(patient.getAddress());

        return patientRepository.save(existingPatient);
    }

    public List<Patient> getPatientByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }



}