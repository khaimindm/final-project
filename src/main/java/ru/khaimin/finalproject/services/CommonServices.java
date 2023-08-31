package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.repositories.CommonRepository;
import ru.khaimin.finalproject.security.PersonDetails;

import java.util.List;

@Service
public class CommonServices {
    
    private final CommonRepository commonRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;        
    }

    public List<String> loadSpecialties() {
        List<String> specialties = commonRepository.getListOfSpecialties();
        return specialties;
    }

    public Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
    
}
