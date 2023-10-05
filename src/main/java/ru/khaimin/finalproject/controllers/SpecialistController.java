package ru.khaimin.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.services.CommonServices;

@Controller
public class SpecialistController {
    private final CommonServices commonServices;

    @Autowired
    public SpecialistController(CommonServices commonServices) {
        this.commonServices = commonServices;
    }
    @GetMapping("/main_specialist")
    public String mainSpecialist(Model model) {
        return "main_specialist";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
