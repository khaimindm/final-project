package ru.khaimin.finalproject.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class DataForWorkTime {
    private int id;
    private String lastName;
    private String firstName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfWork;

    private String workTimeMorning;

    private String workTimeAfternoon;

    private int appointmentInterval;

    public DataForWorkTime() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getWorkTimeMorning() {
        return workTimeMorning;
    }

    public void setWorkTimeMorning(String workTimeMorning) {
        this.workTimeMorning = workTimeMorning;
    }

    public String getWorkTimeAfternoon() {
        return workTimeAfternoon;
    }

    public void setWorkTimeAfternoon(String workTimeAfternoon) {
        this.workTimeAfternoon = workTimeAfternoon;
    }

    public int getAppointmentInterval() {
        return appointmentInterval;
    }

    public void setAppointmentInterval(int appointmentInterval) {
        this.appointmentInterval = appointmentInterval;
    }
    

}
