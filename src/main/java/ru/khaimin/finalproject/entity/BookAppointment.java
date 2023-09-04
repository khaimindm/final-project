package ru.khaimin.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "book_appointment")
public class BookAppointment {
    @Id
    @Column(name = "book_appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookAppointmentId;

    @Column(name = "date_of_appointment")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAppointment;

    @Column(name = "time_start_at")
    private LocalTime timeStartAt;

    @Column(name = "time_end_at")
    private LocalTime timeEndAt;

    @ManyToOne
    @JoinColumn(name = "persons_person_id", referencedColumnName = "person_id")
    private Person person;
    
    @OneToOne
    @JoinColumn(name = "work_time_id", referencedColumnName = "work_time_id")
    private WorkTime workTime;

    public BookAppointment() {

    }

    public int getBookAppointmentId() {
        return bookAppointmentId;
    }

    public void setBookAppointmentId(int bookAppointmentId) {
        this.bookAppointmentId = bookAppointmentId;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public LocalTime getTimeStartAt() {
        return timeStartAt;
    }

    public void setTimeStartAt(LocalTime timeStartAt) {
        this.timeStartAt = timeStartAt;
    }

    public LocalTime getTimeEndAt() {
        return timeEndAt;
    }

    public void setTimeEndAt(LocalTime timeEndAt) {
        this.timeEndAt = timeEndAt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }
    
}
