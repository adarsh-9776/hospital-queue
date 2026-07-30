package com.hospital.hospital_queue.repository;

import com.hospital.hospital_queue.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QueueRepository extends JpaRepository<Queue, Integer> {
    @Query("SELECT COALESCE(MAX(q.tokenNumber), 0) FROM Queue q WHERE q.clinic.id = :clinicId")
    Integer getLastTokenNumberByClinicId(@Param("clinicId") Integer clinicId);

}