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

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "work_time")
public class WorkTime {
    @Id
    @Column(name = "work_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workTimeId;
    
    @Column(name = "date_of_work")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfWork;

    @Column(name = "work_time_start_at")
    private LocalTime workTimeStartAt;

    @Column(name = "work_time_end_at")
    private LocalTime workTimeEndAt;

    @Column(name = "availability")
    private boolean availability;

    @ManyToOne
    @JoinColumn(name = "persons_person_id", referencedColumnName = "person_id")
    private Person person;

    @OneToOne(mappedBy = "workTime")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private BookAppointment bookAppointment;

    public WorkTime() {

    }

    public int getWorkTimeId() {
        return workTimeId;
    }

    public void setWorkTimeId(int workTimeId) {
        this.workTimeId = workTimeId;
    }

    public LocalDate getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(LocalDate dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public LocalTime getWorkTimeStartAt() {
        return workTimeStartAt;
    }

    public void setWorkTimeStartAt(LocalTime workTimeStartAt) {
        this.workTimeStartAt = workTimeStartAt;
    }

    public LocalTime getWorkTimeEndAt() {
        return workTimeEndAt;
    }

    public void setWorkTimeEndAt(LocalTime workTimeEndAt) {
        this.workTimeEndAt = workTimeEndAt;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BookAppointment getBookAppointment() {
        return bookAppointment;
    }

    public void setBookAppointment(BookAppointment bookAppointment) {
        this.bookAppointment = bookAppointment;
    }
    
}
