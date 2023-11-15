package ru.khaimin.finalproject.entity;

import java.time.LocalDate;

public class AppointmentBook {
    public LocalDate dateOfAppointment;
    public String specialtyName;
    public String specialistsFirstName;
    public String specialistsLastName;
    public int medicalCardId;

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

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

    public int getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(int medicalCardId) {
        this.medicalCardId = medicalCardId;
    }
}
