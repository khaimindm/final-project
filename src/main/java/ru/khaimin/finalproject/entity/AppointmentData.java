package ru.khaimin.finalproject.entity;

public class AppointmentData {
    private int bookingListId;
    private String firstName;
    private String lastName;
    private String time;

    public int getBookingListId() {
        return bookingListId;
    }
    public void setBookingListId(int bookingListId) {
        this.bookingListId = bookingListId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}
