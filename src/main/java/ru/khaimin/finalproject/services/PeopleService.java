package ru.khaimin.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.security.PersonDetails;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> loadUser(String username) {
        Optional<Person> person = peopleRepository.findByUsername(username);
        return person;
    }

    public List<Person> getAllSpecialists() {        
        return peopleRepository.findAllByRole("ROLE_SPECIALIST");
    }
    
}
