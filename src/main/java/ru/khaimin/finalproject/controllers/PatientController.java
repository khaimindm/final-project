package ru.khaimin.finalproject.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @GetMapping(
        value = "/specialists/availableTimeByDate",
        produces = "application/json"
        )
    @ResponseBody 
    public String availableTimeByDate(@RequestParam HashMap<String, Object> obj) throws JsonMappingException, JsonProcessingException {
        //HashMap<String, Object> map = new HashMap<>();
        //ObjectMapper objectMapper = new ObjectMapper();
        //map = objectMapper.readValue(timeForProcessing, HashMap.class);
        String processingDate = obj.get("processingDate").toString();
        String specialtyName = obj.get("specialtyName").toString();
        System.out.println("processingDate: " + processingDate);
        System.out.println("specialtyName: " + specialtyName);
        //System.out.println("Содер;имое map: " + map.get(0));
        //String json = objectMapper.writeValueAsString(map);
        //System.out.println("Строка json: " + json);
        //return json;
        return "{\"test\": \"Hello\"}";
    }
    /* public List<LocalTime> availableTimeByDate(@ModelAttribute("dateAndSpecialtyNameForProcessing") DateAndSpecialtyNameForProcessing dateAndSpecialtyNameForProcessing) {
        return commonServices.getAvailableTimeByDateAndSpecialtyName(dateAndSpecialtyNameForProcessing.getDate(), dateAndSpecialtyNameForProcessing.getSpecialtyName());
    } */

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
