package com.hospital.hospital_queue.repository;

import com.hospital.hospital_queue.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Integer> {
}