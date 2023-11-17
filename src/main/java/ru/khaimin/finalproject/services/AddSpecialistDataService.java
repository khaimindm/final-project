package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.ProfessionalActivityRepository;

@Service
public class AddSpecialistDataService {
    public int personId;
    private final ProfessionalActivityRepository professionalActivityRepository;
    private final PeopleRepository peopleRepository;
    private Person personToAddData;


    @Autowired
    public AddSpecialistDataService(ProfessionalActivityRepository professionalActivityRepository, PeopleRepository peopleRepository) {
        this.professionalActivityRepository = professionalActivityRepository;
        this.peopleRepository = peopleRepository;
    }

    public void addData(ProfessionalActivity professionalActivity) {
        professionalActivityRepository.save(professionalActivity);
    }

    public String getPersonName() {
        return null;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Person getPersonToAddData() {
        return personToAddData;
    }

    public void setPersonToAddData(Person personToAddData) {
        this.personToAddData = personToAddData;
    }
    
}
