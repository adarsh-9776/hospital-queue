package com.hospital.hospital_queue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clinicName;

    private String doctorName;

    private String phone;

    private LocalTime openingTime;

    private LocalTime lunchStart;

    private LocalTime lunchEnd;

    private LocalTime closingTime;

    private Integer averageTimePerPatient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(LocalTime lunchStart) {
        this.lunchStart = lunchStart;
    }

    public LocalTime getLunchEnd() {
        return lunchEnd;
    }

    public void setLunchEnd(LocalTime lunchEnd) {
        this.lunchEnd = lunchEnd;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public Integer getAverageTimePerPatient() {
        return averageTimePerPatient;
    }

    public void setAverageTimePerPatient(Integer averageTimePerPatient) {
        this.averageTimePerPatient = averageTimePerPatient;
    }
}