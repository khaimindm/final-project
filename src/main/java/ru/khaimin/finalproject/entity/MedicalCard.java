package ru.khaimin.finalproject.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MedicalCard {
    @Id
    @Column(name = "medical_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicalCardId;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "examination")
    private String examination;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "assigned_therapy")
    private String assignedTherapy;

    @Column(name = "date_of_appointment")
    private LocalDate dateOfAppointment;

    @Column(name = "specialist_id")
    private int specialistId;

    public int getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(int medicalCardId) {
        this.medicalCardId = medicalCardId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAssignedTherapy() {
        return assignedTherapy;
    }

    public void setAssignedTherapy(String assignedTherapy) {
        this.assignedTherapy = assignedTherapy;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }
    
}
