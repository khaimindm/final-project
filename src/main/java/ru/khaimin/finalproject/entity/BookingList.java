package ru.khaimin.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking_list")
public class BookingList {
    @Id
    @Column(name = "booking_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingListId;

    @ManyToOne
    @JoinColumn(name = "persons_person_id", referencedColumnName = "person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name = "work_time_id", referencedColumnName = "work_time_id")
    private WorkTime workTime;

    @Column(name = "completed")
    private boolean completed;

    public BookingList() {

    }

    public int getBookingListId() {
        return bookingListId;
    }

    public void setBookingListId(int bookingListId) {
        this.bookingListId = bookingListId;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
