package ru.khaimin.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.services.CommonServices;

@Controller
public class CommonActionsController {

    private final CommonServices commonServices;
    
    @Autowired
    public CommonActionsController(CommonServices commonServices) {
        this.commonServices = commonServices;
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
