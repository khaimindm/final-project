package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.repositories.SpecialistDataRepository;

@Service
public class AddSpecialistDataService {
    public int personId;
    private final SpecialistDataRepository specialistDataRepository;

    @Autowired
    public AddSpecialistDataService(SpecialistDataRepository specialistDataRepository) {
        this.specialistDataRepository = specialistDataRepository;
    }

    public void addData(ProfessionalActivity professionalActivity) {
        /* if (personId == 0) {
        } */

        professionalActivity.setPersonId(personId);

        specialistDataRepository.save(professionalActivity);
        personId = 0;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
