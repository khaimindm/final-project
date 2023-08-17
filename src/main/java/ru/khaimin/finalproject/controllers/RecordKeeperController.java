package ru.khaimin.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.services.AddSpecialistDataService;

@Controller
public class RecordKeeperController {  
    
    private final AddSpecialistDataService addSpecialistDataService;
    
    @Autowired
    public RecordKeeperController(AddSpecialistDataService addSpecialistDataService) {
        this.addSpecialistDataService = addSpecialistDataService;
    }

    @GetMapping("/main_record_keeper")
    public String mainRecordKeeper() {
        return "main_record_keeper";
    }

    @GetMapping("adding_specialist_data")
    public String addingSpecialistData(@ModelAttribute("professionalActivity")
                                       ProfessionalActivity professionalActivity) {
        if (addSpecialistDataService.getPersonId() == 0) {
            return "redirect:/auth/registration_record_keeper";
        }
        return "adding_specialist_data";
    }
    
    @PostMapping("/adding_specialist_data")
    public String addSpecialistData(@ModelAttribute("professionalActivity") ProfessionalActivity professionalActivity) {
        addSpecialistDataService.addData(professionalActivity);

        return "redirect:/main_record_keeper";
    }
}
