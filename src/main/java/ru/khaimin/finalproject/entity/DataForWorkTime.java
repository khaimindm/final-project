package ru.khaimin.finalproject.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataForWorkTime {
    @Id
    private int dataForWorkTimeId;
    private String lastName;
    private String firstName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfWork;

    private Boolean workTimeMorning;

    private Boolean workTimeAfternoon;

    private int appointmentInterval;

    public DataForWorkTime() {
        
    }

    public int getDataForWorkTimeId() {
        return dataForWorkTimeId;
    }

    public void setDataForWorkTimeId(int dataForWorkTimeId) {
        this.dataForWorkTimeId = dataForWorkTimeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(LocalDate dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public Boolean getWorkTimeMorning() {
        return workTimeMorning;
    }

    public void setWorkTimeMorning(Boolean workTimeMorning) {
        this.workTimeMorning = workTimeMorning;
    }

    public Boolean getWorkTimeAfternoon() {
        return workTimeAfternoon;
    }

    public void setWorkTimeAfternoon(Boolean workTimeAfternoon) {
        this.workTimeAfternoon = workTimeAfternoon;
    }

    public int getAppointmentInterval() {
        return appointmentInterval;
    }

    public void setAppointmentInterval(int appointmentInterval) {
        this.appointmentInterval = appointmentInterval;
    }
}
