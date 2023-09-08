package ru.khaimin.finalproject.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.khaimin.finalproject.entity.BookAppointment;
import ru.khaimin.finalproject.entity.DateAndSpecialtyNameForProcessing;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.PatientService;

@Controller
public class PatientController {
    private final CommonServices commonServices;        
    
    @Autowired
    public PatientController(CommonServices commonServices) {
        this.commonServices = commonServices;
    }
    
    @GetMapping("/main_patient")
    public String mainPatient(Model model) {
        List<String> specialties = commonServices.loadSpecialties();
        model.addAttribute("specialties", specialties);

        return "/main_patient";
    }

    @PostMapping(path = "/availableTimeByDate", consumes = "application/json")
    @ResponseBody
    public List<LocalTime> availableTimeByDate(@ModelAttribute("dateAndSpecialtyNameForProcessing") DateAndSpecialtyNameForProcessing dateAndSpecialtyNameForProcessing) {
        return commonServices.getAvailableTimeByDateAndSpecialtyName(dateAndSpecialtyNameForProcessing.getDate(), dateAndSpecialtyNameForProcessing.getSpecialtyName());
    }

    @GetMapping("/specialists/{specialtyName}")
    public String bookAnAppointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);
        LocalDate currentDate = LocalDate.now();
        //model.addAttribute("currentDate", currentDate);
        
        //List<LocalTime> availableTimes = commonServices.getAvailableTimeByDateAndSpecialtyName(currentDate, specialtyName);

        //List<WorkTime> availableWorkTimes = commonServices.getAvailableWorkTimeBySpecialtyNameAndDateOfWork(specialtyName, currentDate);
        //availableWorkTimes.get(0).getDateOfWork();
        //List<LocalTime> availableTimes = commonServices.getAvailableTimes(availableWorkTimes);
        //model.addAttribute("availableTimes", availableTimes);
        //model.addAttribute("availableWorkTimes", availableWorkTimes);

        //BookAppointment bookAppointment = new BookAppointment();
        //ommonServices.getCurrentUser();        

        //model.addAttribute("bookAppointment", bookAppointment);
        //LocalTime selectedTime = availableTimes.get(0);
        //model.addAttribute("selectedTime", selectedTime);
        
        //commonServices.getAvailableWorkTimeBySpecialtyNameAndDateAndTime(specialtyName, currentDate, selectedTime);

        return "book_an_appointment";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
