package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.repositories.PeopleRepository;

@Service
public class RegistrationService {
    
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddSpecialistDataService addSpecialistDataService;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder,
                               AddSpecialistDataService addSpecialistDataService) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
        this.addSpecialistDataService = addSpecialistDataService;
    }

    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        
        peopleRepository.save(person);
        if (person.getRole().equals("ROLE_SPECIALIST")) {
            addSpecialistDataService.setPersonId(person.getId());
            addSpecialistDataService.setPersonToAddData(person);
        }
    }
}
