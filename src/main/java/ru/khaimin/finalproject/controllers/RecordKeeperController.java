package ru.khaimin.finalproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.khaimin.finalproject.entity.Person;
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
    public String mainRecordKeeper(Model model) {
        List<String> specialties = commonServices.loadSpecialties();
        model.addAttribute("specialties", specialties );

        return "main_record_keeper";
    }

    @GetMapping("/{specialtyName}")
    public String appointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);
        return "appointment";
    }

    @GetMapping("adding_specialist_data")
    public String addingSpecialistData(@ModelAttribute("professionalActivity")
                                       ProfessionalActivity professionalActivity, Model model) {
        if (addSpecialistDataService.getPersonId() == 0) {
            return "redirect:/auth/registration_record_keeper";
        }
        Person currentPerson = addSpecialistDataService.getPersonToAddData();
        model.addAttribute("person_first_last_name", currentPerson.getFirstName() + " " + currentPerson.getLastName());
        return "adding_specialist_data";
    }
    
    @PostMapping("/adding_specialist_data")
    public String addSpecialistData(@ModelAttribute("professionalActivity") ProfessionalActivity professionalActivity) {
        professionalActivity.setPerson(addSpecialistDataService.getPersonToAddData());

        addSpecialistDataService.addData(professionalActivity);
        addSpecialistDataService.getPersonToAddData().setProfessionalActivity(professionalActivity);
        commonServices.setNextAction("/main_record_keeper");
        addSpecialistDataService.setPersonToAddData(null);

        return "redirect:/successful_action_page";
    }
}
