package ru.khaimin.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.PatientService;

import java.util.List;

@Controller
public class PatientController {
    private final CommonServices commonServices;
    
    @Autowired
    public PatientController(CommonServices commonServices, PatientService patientService) {
        this.commonServices = commonServices;
    }
    
    @GetMapping("/main_patient")
    public String mainPatient(Model model) {
        List<String> specialties = commonServices.loadSpecialties();
        model.addAttribute("specialties", specialties);

        return "/main_patient";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
