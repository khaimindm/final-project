package ru.khaimin.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.khaimin.finalproject.entity.DataForWorkTime;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.security.PersonDetails;
import ru.khaimin.finalproject.services.AddSpecialistDataService;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.PeopleService;
import ru.khaimin.finalproject.services.RecordKeeperService;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/recordkeeper")
public class RecordKeeperController {  
    
    private final AddSpecialistDataService addSpecialistDataService;
    private final CommonServices commonServices;
    private final PeopleService peopleService;
    private final RecordKeeperService recordKeeperService;

    @Autowired
    public RecordKeeperController(AddSpecialistDataService addSpecialistDataService, CommonServices commonServices,
                                  PeopleService peopleService, RecordKeeperService recordKeeperService) {
        this.addSpecialistDataService = addSpecialistDataService;
        this.commonServices = commonServices;
        this.peopleService = peopleService;
        this.recordKeeperService = recordKeeperService;
    }

    @GetMapping("/main_record_keeper")
    public String mainRecordKeeper(Model model) {
        List<String> specialties = commonServices.loadSpecialties();
        model.addAttribute("specialties", specialties);
        //model.addAttribute("currentUser", commonServices.getCurrentUser());
        
        return "main_record_keeper";
    }

    @GetMapping("/{specialtyName}")
    public String appointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);
        return "appointment";
    }

    @GetMapping("/adding_specialist_data")
    public String addingSpecialistData(@ModelAttribute("professionalActivity")
                                       ProfessionalActivity professionalActivity, Model model) {
        if (addSpecialistDataService.getPersonId() == 0) {
            return "redirect:/auth/registration_record_keeper";
        }
        Person currentPerson = addSpecialistDataService.getPersonToAddData();
        model.addAttribute("person_first_last_name", currentPerson.getFirstName() + " " + currentPerson.getLastName());
        //model.addAttribute("currentUser", commonServices.getCurrentUser());
        return "adding_specialist_data";
    }
    
    @PostMapping("/adding_specialist_data")
    public String addSpecialistData(@ModelAttribute("professionalActivity") ProfessionalActivity professionalActivity,
                                    Model model) {
        professionalActivity.setPerson(addSpecialistDataService.getPersonToAddData());

        addSpecialistDataService.addData(professionalActivity);
        addSpecialistDataService.getPersonToAddData().setProfessionalActivity(professionalActivity);
        addSpecialistDataService.setPersonToAddData(null);

        String action = "/main_record_keeper";
        model.addAttribute("action", action);

        return "/successful_action_page";
    }

    @GetMapping("/specialists")
    public String allSpecialists(Model model) {
        List<Person> allSpecialists = peopleService.getAllSpecialists();
        model.addAttribute("specialists", allSpecialists);
        return "/list_of_specialists";
    }

    @GetMapping("/specialist/{id}/work_time")
    public String workTime(@PathVariable("id") int id, Model model) {
        Optional<Person> person = recordKeeperService.getPersonById(id);
        DataForWorkTime dataForWorkTime = new DataForWorkTime();
        dataForWorkTime.setId(person.orElse(new Person()).getId());
        dataForWorkTime.setLastName(person.orElse(new Person()).getLastName());
        dataForWorkTime.setFirstName(person.orElse(new Person()).getFirstName());

        model.addAttribute("dataForWorkTime", dataForWorkTime);
        return "/work_time";
    }

    @PostMapping("/specialist/work_time")
    public String addWorkTime(@ModelAttribute("dataForWorkTime") DataForWorkTime dataForWorkTime) {
        
        Person person = recordKeeperService.getPersonById(dataForWorkTime.getId()).orElse(new Person());
        recordKeeperService.workTime(person, dataForWorkTime.getDateOfWork(), dataForWorkTime.getWorkTimeMorning(),
                                     dataForWorkTime.getWorkTimeAfternoon(), dataForWorkTime.getAppointmentInterval());
        
        return "redirect:/successful_action_page";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
