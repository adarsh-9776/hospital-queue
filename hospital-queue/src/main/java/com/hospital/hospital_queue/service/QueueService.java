package com.hospital.hospital_queue.service;

import com.hospital.hospital_queue.dto.DashboardSummaryResponse;
import com.hospital.hospital_queue.model.Clinic;
import com.hospital.hospital_queue.model.Queue;
import com.hospital.hospital_queue.repository.ClinicRepository;
import com.hospital.hospital_queue.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalTime;
import com.hospital.hospital_queue.exception.ResourceNotFoundException;

@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final ClinicRepository clinicRepository;

    public QueueService(QueueRepository queueRepository,
                        ClinicRepository clinicRepository) {
        this.queueRepository = queueRepository;
        this.clinicRepository = clinicRepository;
    }

    public Queue addQueue(Queue queue) {

        Integer lastToken = queueRepository.getLastTokenNumberByClinicIdAndDate(
                queue.getClinic().getId(),
                LocalDate.now()
        );

        Integer newToken = lastToken + 1;


        queue.setTokenNumber(newToken);

        Clinic clinic = clinicRepository.findById(queue.getClinic().getId()).get();

        LocalTime appointmentTime =
                calculateAppointmentTime(clinic, newToken);

        queue.setAppointmentTime(appointmentTime);
        queue.setQueueDate(LocalDate.now());

        queue.setStatus("WAITING");

        return queueRepository.save(queue);
    }

    public List<Queue> getAllQueues() {

        return queueRepository.findAll();
    }

    public List<Queue> getQueuesByClinicId(int clinicId) {
        return queueRepository.findByClinicIdOrderByTokenNumberAsc(clinicId);

    }

    public Queue getCurrentPatient(Integer clinicId) {
        return queueRepository.findFirstByClinicIdAndStatusOrderByTokenNumberAsc(
                clinicId,
                "CALLED"
        );
    }


    public Queue getQueueById(int id) {

        return queueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue not found with id: " + id)
                );
    }

    public Queue updateQueue(int id, Queue queue) {

        Queue existingQueue = queueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue not found with id: " + id)
                );

        existingQueue.setTokenNumber(queue.getTokenNumber());
        existingQueue.setStatus(queue.getStatus());
        existingQueue.setAppointmentTime(queue.getAppointmentTime());

        return queueRepository.save(existingQueue);
    }

    public void deleteQueue(int id) {

        Queue queue = queueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue not found with id: " + id)
                );

        queueRepository.delete(queue);
    }

    private LocalTime calculateAppointmentTime(Clinic clinic, Integer tokenNumber) {

        LocalTime openingTime = clinic.getOpeningTime();
        LocalTime lunchStart = clinic.getLunchStart();
        LocalTime lunchEnd = clinic.getLunchEnd();

        int averageTime = clinic.getAverageTimePerPatient();

        int minutesBeforeLunch =
                (int) java.time.Duration.between(openingTime, lunchStart).toMinutes();

        int patientsBeforeLunch = minutesBeforeLunch / averageTime;

        if (tokenNumber <= patientsBeforeLunch) {

            return openingTime.plusMinutes(
                    (long) (tokenNumber - 1) * averageTime
            );
        }

        int patientsAfterLunch = tokenNumber - patientsBeforeLunch - 1;

        return lunchEnd.plusMinutes(
                (long) patientsAfterLunch * averageTime
        );
    }

    public List<Queue> getTodayQueues(Integer clinicId) {
        return queueRepository.findByClinicIdAndQueueDateOrderByTokenNumberAsc(
                clinicId,
                LocalDate.now()
        );
    }

    public List<Queue> searchTodayQueueByPhone(String phone) {
        return queueRepository.findByPatientPhoneAndQueueDate(
                phone,
                LocalDate.now()
        );
    }

    public List<Queue> getTodayWaitingQueues(Integer clinicId) {
        return queueRepository.findByClinicIdAndQueueDateAndStatusOrderByTokenNumberAsc(
                clinicId,
                LocalDate.now(),
                "WAITING"
        );
    }

    public List<Queue> getTodayCompletedQueues(Integer clinicId) {
        return queueRepository.findByClinicIdAndQueueDateAndStatus(
                clinicId,
                LocalDate.now(),
                "COMPLETED"
        );
    }

    public long getTodayQueueCount(Integer clinicId) {
        return queueRepository.countByClinicIdAndQueueDate(
                clinicId,
                LocalDate.now()
        );
    }

    public DashboardSummaryResponse getDashboardSummary(Integer clinicId) {

        DashboardSummaryResponse response = new DashboardSummaryResponse();

        response.setTotalPatients(
                queueRepository.countByClinicIdAndQueueDate(
                        clinicId,
                        LocalDate.now()
                )
        );

        response.setWaitingPatients(
                queueRepository.countByClinicIdAndQueueDateAndStatus(
                        clinicId,
                        LocalDate.now(),
                        "WAITING"
                )
        );

        response.setCalledPatients(
                queueRepository.countByClinicIdAndQueueDateAndStatus(
                        clinicId,
                        LocalDate.now(),
                        "CALLED"
                )
        );

        response.setCompletedPatients(
                queueRepository.countByClinicIdAndQueueDateAndStatus(
                        clinicId,
                        LocalDate.now(),
                        "COMPLETED"
                )
        );

        return response;
    }

    public Queue callNextPatient(Integer clinicId) {

        Queue queue = queueRepository
                .findFirstByClinicIdAndQueueDateAndStatusOrderByTokenNumberAsc(
                        clinicId,
                        LocalDate.now(),
                        "WAITING"
                );

        if (queue == null) {
            throw new ResourceNotFoundException(
                    "No waiting patient found for clinic id: " + clinicId
            );
        }

        queue.setStatus("CALLED");

        return queueRepository.save(queue);
    }

    public Queue completeCurrentPatient(Integer clinicId) {


        Queue queue = queueRepository
                .findFirstByClinicIdAndStatusOrderByTokenNumberAsc(
                        clinicId,
                        "CALLED"
                );

        if (queue == null) {
            throw new ResourceNotFoundException(
                    "No called patient found for clinic id: " + clinicId
            );
        }

        queue.setStatus("COMPLETED");

        return queueRepository.save(queue);

    }

    public Queue getCurrentCalledPatient(Integer clinicId) {

        return queueRepository.findFirstByClinicIdAndQueueDateAndStatus(
                clinicId,
                LocalDate.now(),
                "CALLED"
        );
    }

    public Queue cancelQueue(Integer queueId) {

        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue not found with id: " + queueId)
                );



        queue.setStatus("CANCELLED");

        return queueRepository.save(queue);
    }

    public List<Queue> getQueueHistory(Integer clinicId) {

        return queueRepository.findByClinicIdOrderByQueueDateDescTokenNumberAsc(clinicId);
    }

    public List<Queue> getQueuesByStatus(Integer clinicId, String status) {

        return queueRepository
                .findByClinicIdAndStatusOrderByQueueDateDescTokenNumberAsc(
                        clinicId,
                        status
                );
    }


}

