package com.hospital.hospital_queue.repository;

import com.hospital.hospital_queue.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}