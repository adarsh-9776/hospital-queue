package com.hospital.hospital_queue.service;

import com.hospital.hospital_queue.model.Clinic;
import com.hospital.hospital_queue.model.Queue;
import com.hospital.hospital_queue.repository.ClinicRepository;
import com.hospital.hospital_queue.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalTime;

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

        Integer lastToken = queueRepository.getLastTokenNumberByClinicId(
                queue.getClinic().getId()
        );

        Integer newToken = lastToken + 1;

        queue.setTokenNumber(newToken);

        Clinic clinic = clinicRepository.findById(queue.getClinic().getId()).get();

        LocalTime appointmentTime =
                calculateAppointmentTime(clinic, newToken);

        queue.setAppointmentTime(appointmentTime);

        queue.setStatus("WAITING");

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
        existingQueue.setAppointmentTime(queue.getAppointmentTime());

        return queueRepository.save(existingQueue);
    }

    public void deleteQueue(int id) {
        queueRepository.deleteById(id);
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
}