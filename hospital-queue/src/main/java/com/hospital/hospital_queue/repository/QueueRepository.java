package com.hospital.hospital_queue.repository;

import com.hospital.hospital_queue.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDate;



public interface QueueRepository extends JpaRepository<Queue, Integer> {
    @Query("SELECT COALESCE(MAX(q.tokenNumber), 0) FROM Queue q WHERE q.clinic.id = :clinicId")
    Integer getLastTokenNumberByClinicId(@Param("clinicId") Integer clinicId);

    List<Queue> findByClinicIdOrderByTokenNumberAsc(Integer clinicId);

    Queue findFirstByClinicIdAndStatusOrderByTokenNumberAsc(
            Integer clinicId,
            String status
    );

    List<Queue> findByClinicIdAndQueueDateOrderByTokenNumberAsc(
            Integer clinicId,
            LocalDate queueDate
    );

    List<Queue> findByPatientPhoneAndQueueDate(String phone, LocalDate queueDate);

    List<Queue> findByClinicIdAndQueueDateAndStatusOrderByTokenNumberAsc(
            Integer clinicId,
            LocalDate queueDate,
            String status
    );

    List<Queue> findByClinicIdAndQueueDateAndStatus(
            Integer clinicId,
            LocalDate queueDate,
            String status
    );

    long countByClinicIdAndQueueDate(
            Integer clinicId,
            LocalDate queueDate
    );

}