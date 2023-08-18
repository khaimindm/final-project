package ru.khaimin.finalproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.services.AddSpecialistDataService;
import ru.khaimin.finalproject.services.CommonServices;

@Controller
public class RecordKeeperController {  
    
    private final AddSpecialistDataService addSpecialistDataService;
    private final CommonServices commonServices;
    
    @Autowired
    public RecordKeeperController(AddSpecialistDataService addSpecialistDataService, CommonServices commonServices) {
        this.addSpecialistDataService = addSpecialistDataService;
        this.commonServices = commonServices;
    }

    @GetMapping("/main_record_keeper")
    public String mainRecordKeeper() {
        List<String> testsList = commonServices.loadSpecialties();
        for (String string : testsList) {
            System.out.println(string);
        }
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
