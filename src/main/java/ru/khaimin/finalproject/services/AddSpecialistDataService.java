package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.repositories.SpecialistDataRepository;

@Service
public class AddSpecialistDataService {
    private final SpecialistDataRepository specialistDataRepository;

    @Autowired
    public AddSpecialistDataService(SpecialistDataRepository specialistDataRepository) {
        this.specialistDataRepository = specialistDataRepository;
    }

    public void addData(ProfessionalActivity professionalActivity) {        
    }
}
