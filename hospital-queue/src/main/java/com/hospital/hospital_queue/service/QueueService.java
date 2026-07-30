package com.hospital.hospital_queue.service;

import com.hospital.hospital_queue.model.Queue;
import com.hospital.hospital_queue.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    private final QueueRepository queueRepository;

    public QueueService(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    public Queue addQueue(Queue queue) {
        return queueRepository.save(queue);
    }

    public List<Queue> getAllQueues() {
        return queueRepository.findAll();
    }

    public Queue getQueueById(int id) {
        return queueRepository.findById(id).get();
    }

    public Queue updateQueue(int id, Queue queue) {

        Queue existingQueue = queueRepository.findById(id).get();

        existingQueue.setTokenNumber(queue.getTokenNumber());
        existingQueue.setStatus(queue.getStatus());
        existingQueue.setWaitingTime(queue.getWaitingTime());

        return queueRepository.save(existingQueue);
    }

    public void deleteQueue(int id) {
        queueRepository.deleteById(id);
    }
}