package com.hospital.hospital_queue.service;

import com.hospital.hospital_queue.repository.ClinicRepository;
import com.hospital.hospital_queue.model.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;


    public Clinic saveClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic updateClinic(int id, Clinic clinic) {

        Clinic existingClinic = clinicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        existingClinic.setClinicName(clinic.getClinicName());
        existingClinic.setDoctorName(clinic.getDoctorName());
        existingClinic.setPhone(clinic.getPhone());
        existingClinic.setOpeningTime(clinic.getOpeningTime());
        existingClinic.setLunchStart(clinic.getLunchStart());
        existingClinic.setLunchEnd(clinic.getLunchEnd());
        existingClinic.setClosingTime(clinic.getClosingTime());
        existingClinic.setAverageTimePerPatient(clinic.getAverageTimePerPatient());

        return clinicRepository.save(existingClinic);
    }
}