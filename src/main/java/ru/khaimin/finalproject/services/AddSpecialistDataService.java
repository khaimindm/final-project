package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.SpecialistDataRepository;

@Service
public class AddSpecialistDataService {
    public int personId;
    private final SpecialistDataRepository specialistDataRepository;
    private final PeopleRepository peopleRepository;
    private Person personToAddData;


    @Autowired
    public AddSpecialistDataService(SpecialistDataRepository specialistDataRepository, PeopleRepository peopleRepository) {
        this.specialistDataRepository = specialistDataRepository;
        this.peopleRepository = peopleRepository;
    }

    public void addData(ProfessionalActivity professionalActivity) {
        /* if (personId == 0) {
        } */

        //professionalActivity.setPersonId(personId);

        specialistDataRepository.save(professionalActivity);
        personId = 0;
    }

    public String getPersonName() {
        //return person.getFirstName().toString() + person.getLastName().toString();
        
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
