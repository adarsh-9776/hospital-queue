package com.hospital.hospital_queue.service;

import com.hospital.hospital_queue.repository.ClinicRepository;
import com.hospital.hospital_queue.model.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;


    public Clinic saveClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }
}