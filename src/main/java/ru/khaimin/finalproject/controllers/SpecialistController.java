package ru.khaimin.finalproject.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.SpecialistService;

@Controller
public class SpecialistController {
    private final CommonServices commonServices;
    private final SpecialistService specialistService;

    @Autowired
    public SpecialistController(CommonServices commonServices, SpecialistService specialistService) {
        this.commonServices = commonServices;
        this.specialistService = specialistService;
    }

    @GetMapping("/main_specialist")
    public String mainSpecialist(Model model) {
        return "main_specialist";
    }

    @GetMapping(
        value = "/patientListByDate",
        produces = "application/json"
    )
    @ResponseBody
    public String patientListByDate(@RequestParam HashMap<String, Object> obj) {
        String processingDate = obj.get("processingDate").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(processingDate, formatter);

        List<WorkTime> patientList = specialistService.getPatientListByDate(date);
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
