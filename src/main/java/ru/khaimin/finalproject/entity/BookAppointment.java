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

    @ManyToOne
    @JoinColumn(name = "persons_person_id", referencedColumnName = "person_id")
    private Person person;
    
    /*@OneToOne
    @JoinColumn(name = "book_appointment_work_time_id", referencedColumnName = "work_time_id")
    private WorkTime workTime;*/

    public BookAppointment() {

    }

    public int getBookAppointmentId() {
        return bookAppointmentId;
    }

    public void setBookAppointmentId(int bookAppointmentId) {
        this.bookAppointmentId = bookAppointmentId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

   /* public WorkTime getWorkTime() {
        return workTime;
    }*/

    /*public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }*/
    
}
