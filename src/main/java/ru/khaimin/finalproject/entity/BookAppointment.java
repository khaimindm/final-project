package ru.khaimin.finalproject.entity;

import javax.persistence.*;

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
}
