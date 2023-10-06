package ru.khaimin.finalproject.controllers;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("/specialists/{specialtyName}")
    public String bookAnAppointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);

        List<Date> availableDatesOfWork = commonServices.getDatesOfWorkBySpecialtyName(specialtyName);
        model.addAttribute("availableDatesOfWork", availableDatesOfWork);
        
        return "book_an_appointment";
    }

    @PatchMapping("/specialists/bookplace")
    public String bookPlace(@ModelAttribute BookAppointment bookAppointment,
                            @RequestParam("dateOfWork") String bookingDateString,
                            @RequestParam("timeStartAt") String bookingTimeString,
                            @RequestParam("availableSpecialists") int specialistId,
                            Model model) {
        DateTimeFormatter formatterDate= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bookingDate = LocalDate.parse(bookingDateString, formatterDate);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime bookingTime = LocalTime.parse(bookingTimeString, formatterTime);

        WorkTime workTime = patientService.makeBookingBySpecialistIdAndBookingDateAndBookingTime(specialistId, bookingDate, bookingTime);
        commonServices.makeBooking(workTime);

        String action = commonServices.getDefaultPageLinkCurrentUser();
        model.addAttribute("action", action);

        return "/successful_action_page";
    }
    
    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
