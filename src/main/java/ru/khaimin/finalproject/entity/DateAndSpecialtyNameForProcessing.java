package ru.khaimin.finalproject.entity;

import java.time.LocalDate;

import javax.persistence.Entity;


public class DateAndSpecialtyNameForProcessing {
    private LocalDate date;
    private String specialtyName;

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
