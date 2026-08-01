package com.hospital.hospital_queue.dto;

public class DashboardSummaryResponse {

    private long totalPatients;
    private long waitingPatients;
    private long calledPatients;
    private long completedPatients;

    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public long getWaitingPatients() {
        return waitingPatients;
    }

    public void setWaitingPatients(long waitingPatients) {
        this.waitingPatients = waitingPatients;
    }

    public long getCalledPatients() {
        return calledPatients;
    }

    public void setCalledPatients(long calledPatients) {
        this.calledPatients = calledPatients;
    }

    public long getCompletedPatients() {
        return completedPatients;
    }

    public void setCompletedPatients(long completedPatients) {
        this.completedPatients = completedPatients;
    }
}