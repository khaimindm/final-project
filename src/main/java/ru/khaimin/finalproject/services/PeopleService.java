package ru.khaimin.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.ProfessionalActivityRepository;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final ProfessionalActivityRepository professionalActivityRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository,
                         ProfessionalActivityRepository professionalActivityRepository) {
        this.peopleRepository = peopleRepository;
        this.professionalActivityRepository = professionalActivityRepository;
    }

    public Optional<Person> loadUser(String username) {
        Optional<Person> person = peopleRepository.findByUsername(username);
        return person;
    }

    public List<Person> getAllByRole(String role) {
        return peopleRepository.findAllByRole(role);
    }
    
}
