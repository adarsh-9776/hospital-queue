package com.hospital.hospital_queue.controller;

import com.hospital.hospital_queue.model.Queue;
import com.hospital.hospital_queue.service.QueueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/queue")
    public Queue addQueue(@RequestBody Queue queue) {
        return queueService.addQueue(queue);
    }

    @GetMapping("/queue")
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }

    @GetMapping("/queue/{id}")
    public Queue getQueueById(@PathVariable int id) {
        return queueService.getQueueById(id);
    }

    @PutMapping("/queue/{id}")
    public Queue updateQueue(@PathVariable int id, @RequestBody Queue queue) {
        return queueService.updateQueue(id, queue);
    }

    @DeleteMapping("/queue/{id}")
    public String deleteQueue(@PathVariable int id) {
        queueService.deleteQueue(id);

        return "Queue deleted successfully";
    }
}