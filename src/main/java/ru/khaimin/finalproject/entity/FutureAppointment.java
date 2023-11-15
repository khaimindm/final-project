package ru.khaimin.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class FutureAppointment {
    private String specialtyName;
    private String specialistsFirstName;
    private String specialistsLastName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialistsFirstName() {
        return specialistsFirstName;
    }

    public void setSpecialistsFirstName(String specialistsFirstName) {
        this.specialistsFirstName = specialistsFirstName;
    }

    public String getSpecialistsLastName() {
        return specialistsLastName;
    }

    public void setSpecialistsLastName(String specialistsLastName) {
        this.specialistsLastName = specialistsLastName;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
