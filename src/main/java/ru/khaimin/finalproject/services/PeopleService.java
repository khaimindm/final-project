package ru.khaimin.finalproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.security.PersonDetails;

@Service
public class PeopleService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Person> person = peopleRepository.findByLogin(username);
        return new PersonDetails(person.get());
    }
    
}
