package ru.khaimin.finalproject.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {        
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (!peopleService.loadUserByUsername(person.getLogin()).getUsername().isEmpty()) {
            errors.rejectValue("username", "", "Пользователь с таким логином уже существует");
        }
    }
    
}
