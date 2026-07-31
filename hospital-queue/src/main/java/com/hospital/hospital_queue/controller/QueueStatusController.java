package com.hospital.hospital_queue.controller;

import com.hospital.hospital_queue.model.Queue;
import com.hospital.hospital_queue.service.QueueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue-status")
public class QueueStatusController {

    private final QueueService queueService;

    public QueueStatusController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/{id}")
    public Queue getQueueStatus(@PathVariable int id) {
        return queueService.getQueueById(id);
    }

    @PutMapping("/{id}/called")
    public Queue callPatient(@PathVariable int id) {
        Queue queue = queueService.getQueueById(id);
        queue.setStatus("CALLED");
        return queueService.updateQueue(id, queue);
    }

    @PutMapping("/{id}/completed")
    public Queue completePatient(@PathVariable int id) {
        Queue queue = queueService.getQueueById(id);
        queue.setStatus("COMPLETED");
        return queueService.updateQueue(id, queue);
    }
}