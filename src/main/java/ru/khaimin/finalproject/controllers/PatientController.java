package ru.khaimin.finalproject.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.PatientService;

@Controller
public class PatientController {
    private final CommonServices commonServices;
    private final WorkTime workTime;    
    
    @Autowired
    public PatientController(CommonServices commonServices, WorkTime workTime) {
        this.commonServices = commonServices;
        this.workTime = workTime;
    }

    @GetMapping("/specialists/{specialtyName}")
    public String bookAnAppointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);
        LocalDate currentDate = LocalDate.now();
        model.addAttribute("currentDate", currentDate);
        model.addAttribute("availableTimes", commonServices.getAvailableTimeByDate(currentDate));

        return "book_an_appointment";
    }

    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
