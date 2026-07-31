package com.hospital.hospital_queue.repository;

import com.hospital.hospital_queue.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByPhone(String phone);


}