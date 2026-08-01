package com.hospital.hospital_queue.controller;

import com.hospital.hospital_queue.dto.DashboardSummaryResponse;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Queue Management", description = "APIs for managing hospital queues")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }


    @Operation(summary = "Add a new patient to the queue")
    @PostMapping("/queue")
    public Queue addQueue(@RequestBody Queue queue) {
        return queueService.addQueue(queue);
    }

    @Operation(summary = "Get all queue records")
    @GetMapping("/queue")
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }

    @Operation(summary = "Get queue by ID")
    @GetMapping("/queue/{id}")
    public Queue getQueueById(@PathVariable int id) {
        return queueService.getQueueById(id);
    }


    @Operation(summary = "Update queue details")
    @PutMapping("/queue/{id}")
    public Queue updateQueue(@PathVariable int id, @RequestBody Queue queue) {
        return queueService.updateQueue(id, queue);
    }

    @Operation(summary = "Delete queue by ID")
    @DeleteMapping("/queue/{id}")
    public String deleteQueue(@PathVariable int id) {
        queueService.deleteQueue(id);

        return "Queue deleted successfully";
    }

    @Operation(summary = "Get today's queue for a clinic")
    @GetMapping("queue/today/{clinicId}")
    public List<Queue> getTodayQueues(@PathVariable Integer clinicId) {
        return queueService.getTodayQueues(clinicId);
    }

    @GetMapping("/queue/search/{phone}")
    public List<Queue> searchTodayQueueByPhone(@PathVariable String phone) {
        return queueService.searchTodayQueueByPhone(phone);
    }

    @GetMapping("/queue/waiting/{clinicId}")
    public List<Queue> getTodayWaitingQueues(@PathVariable Integer clinicId) {
        return queueService.getTodayWaitingQueues(clinicId);
    }
    @GetMapping("/queue/completed/{clinicId}")
    public List<Queue> getTodayCompletedQueues(@PathVariable Integer clinicId) {
        return queueService.getTodayCompletedQueues(clinicId);
    }

    @GetMapping("/queue/count/{clinicId}")
    public long getTodayQueueCount(@PathVariable Integer clinicId) {
        return queueService.getTodayQueueCount(clinicId);
    }

    @GetMapping("/dashboard/{clinicId}")
    public DashboardSummaryResponse getDashboardSummary(
            @PathVariable Integer clinicId) {

        return queueService.getDashboardSummary(clinicId);
    }


    @Operation(summary = "Call the next waiting patient")
    @PutMapping("/queue/call-next/{clinicId}")
    public Queue callNextPatient(@PathVariable Integer clinicId) {
        return queueService.callNextPatient(clinicId);
    }


    @Operation(summary = "Mark the current called patient as completed")
    @PutMapping("/queue/complete/{clinicId}")
    public Queue completeCurrentPatient(@PathVariable Integer clinicId) {
        return queueService.completeCurrentPatient(clinicId);
    }


    @Operation(summary = "Get the currently called patient")
    @GetMapping("/queue/current-called/{clinicId}")
    public Queue getCurrentCalledPatient(@PathVariable Integer clinicId) {
        return queueService.getCurrentCalledPatient(clinicId);
    }


    @Operation(summary = "Cancel a queue entry")
    @PutMapping("/queue/cancel/{queueId}")
    public Queue cancelQueue(@PathVariable Integer queueId) {
        return queueService.cancelQueue(queueId);
    }


    @Operation(summary = "Get complete queue history for a clinic")
    @GetMapping("/queue/history/{clinicId}")
    public List<Queue> getQueueHistory(@PathVariable Integer clinicId) {
        return queueService.getQueueHistory(clinicId);
    }


    @Operation(summary = "Get queues by status")
    @GetMapping("/queue/status/{clinicId}/{status}")
    public List<Queue> getQueuesByStatus(
            @PathVariable Integer clinicId,
            @PathVariable String status) {

        return queueService.getQueuesByStatus(clinicId, status);
    }
}

