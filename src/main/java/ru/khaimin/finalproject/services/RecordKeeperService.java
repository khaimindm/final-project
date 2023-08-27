package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.repositories.ProfessionalActivityRepository;

import java.util.Optional;

@Service
public class RecordKeeperService {
    private final ProfessionalActivityRepository professionalActivityRepository;

    @Autowired
    public RecordKeeperService(ProfessionalActivityRepository professionalActivityRepository) {
        this.professionalActivityRepository = professionalActivityRepository;
    }

}
