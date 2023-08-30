package ru.khaimin.finalproject.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.services.AddSpecialistDataService;
import ru.khaimin.finalproject.services.RegistrationService;
import ru.khaimin.finalproject.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    
    
    @Autowired
    public AuthController (RegistrationService registrationService, PersonValidator personValidator, AddSpecialistDataService addSpecialistDataService) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
    BindingResult bindingResult) {

        if (person.getRole() == null) {
            person.setRole("ROLE_PATIENT");            
        }

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationService.register(person);

        return "redirect:/auth/login";
    }

    @GetMapping("/registration_record_keeper")
    public String addSpecialist(@ModelAttribute("person") Person person) {
        return "auth/registration_record_keeper";
    } 

    @PostMapping("/registration_record_keeper")
    public String performRegistrationRecordKeeper(@ModelAttribute("person") @Valid Person person,
                                                  BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration_record_keeper";
        }

        registrationService.register(person);

        if (person.getRole().equals("ROLE_SPECIALIST")) {            
            return "redirect:/recordkeeper/adding_specialist_data";
        }
        
        return "redirect:/recordkeeper/main_record_keeper";
    }
}
