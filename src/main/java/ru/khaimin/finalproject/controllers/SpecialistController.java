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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.khaimin.finalproject.entity.MedicalCard;
import ru.khaimin.finalproject.entity.PatientList;
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
    public String patientListByDate(@RequestParam HashMap<String, Object> obj) throws JsonProcessingException {
        String processingDate = obj.get("processingDate").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(processingDate, formatter);

        List<PatientList> patientList = specialistService.getPatientListByDate(date);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(patientList);
    }

    @GetMapping("/appointment/{patientId}")
    public String appointment(@PathVariable("patientId") int patientId, Model model,
    @ModelAttribute("medicalCard") MedicalCard medicalCard) {
        Person patient = specialistService.personById(patientId);
        String patientName = patient.getFirstName() + " " + patient.getLastName();
        model.addAttribute("patientName", patientName);
        model.addAttribute("patientId", patientId);
        model.addAttribute("specialistId", currentPerson().getId());

        return "medical_record";
    }

    @PostMapping("/appointment")
    public String addDetailsOfDoctorsAppointment(@ModelAttribute("medicalCard") MedicalCard medicalCard,
    Model model) {
        specialistService.detailsOfDoctorsAppointment(medicalCard);
        
        String action = "/main_specialist";
        model.addAttribute("action", action);

        return "successful_action_page";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
