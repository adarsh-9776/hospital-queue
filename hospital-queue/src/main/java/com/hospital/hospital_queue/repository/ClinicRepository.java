package com.hospital.hospital_queue.repository;

import com.hospital.hospital_queue.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

}