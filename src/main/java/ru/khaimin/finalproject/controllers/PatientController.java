package ru.khaimin.finalproject.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.khaimin.finalproject.entity.BookAppointment;
import ru.khaimin.finalproject.entity.DateAndSpecialtyNameForProcessing;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.PatientService;
import com.fasterxml.jackson.databind.type.TypeFactory;

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
    
    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
