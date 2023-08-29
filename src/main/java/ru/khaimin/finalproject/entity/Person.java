package ru.khaimin.finalproject.entity;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private ProfessionalActivity professionalActivity;

    @OneToMany(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<WorkTime> workTime;    

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ProfessionalActivity getProfessionalActivity() {
        return professionalActivity;
    }

    public void setProfessionalActivity(ProfessionalActivity professionalActivity) {
        this.professionalActivity = professionalActivity;
    }

    public List<WorkTime> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(List<WorkTime> workTime) {
        this.workTime = workTime;
    }
        
}
