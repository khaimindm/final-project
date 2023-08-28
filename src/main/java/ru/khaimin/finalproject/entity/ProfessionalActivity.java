package ru.khaimin.finalproject.entity;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "professional_activity")
public class ProfessionalActivity {
    @Id
    @Column(name = "specialist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialistId;

    @NotEmpty(message = "Наименование специальности не должно быть пустым")
    @Column(name = "specialty_name")
    private String specialtyName;

    @Column(name = "degree_certificate")
    private String degreeCertificate;

    @Column(name = "date_of_employment")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfEmployment;

    //@Column(name = "persons_person_id")
    //private int personId;

    @OneToOne
    @JoinColumn(name = "persons_person_id", referencedColumnName = "person_id")
    private Person person;

    public ProfessionalActivity() {
        
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getDegreeCertificate() {
        return degreeCertificate;
    }

    public void setDegreeCertificate(String degreeCertificate) {
        this.degreeCertificate = degreeCertificate;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //public int getPersonId() {
    //    return personId;
    //}

    //public void setPersonId(int personId) {
    //    this.personId = personId;
    //}

    
}
