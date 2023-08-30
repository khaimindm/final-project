package ru.khaimin.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.khaimin.finalproject.services.CommonServices;

@Controller
public class CommonActionsController {

    private final CommonServices commonServices;
    
    @Autowired
    public CommonActionsController(CommonServices commonServices) {
        this.commonServices = commonServices;
    }


    @GetMapping("/successful_action_page")
    public String successfulAction(Model model) {
        model.addAttribute("nextAction", commonServices.getNextAction());
        commonServices.setNextAction(null);
        return "successful_action_page";
    }
}
