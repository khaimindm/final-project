package ru.khaimin.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.ProfessionalActivityRepository;
import ru.khaimin.finalproject.security.PersonDetails;

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

    public List<Person> getAllSpecialists() {        
        return peopleRepository.findAllByRole("ROLE_SPECIALIST");
    }


    /*public ProfessionalActivity getProfessionalActivity(Person person) {
        ProfessionalActivity temp = person.getProfessionalActivity();

        //Optional<ProfessionalActivity> foundProfessionalActivity = professionalActivityRepository
        return temp;
    }*/
    
}
