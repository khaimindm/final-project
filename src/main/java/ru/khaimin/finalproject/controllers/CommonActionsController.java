package ru.khaimin.finalproject.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.khaimin.finalproject.entity.BookAppointment;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.services.CommonServices;

@Controller
public class CommonActionsController {

    private final CommonServices commonServices;
    
    @Autowired
    public CommonActionsController(CommonServices commonServices) {
        this.commonServices = commonServices;
    }

    @GetMapping(
        value = "/specialists/availableTimeByDate",
        produces = "application/json"
        )
    @ResponseBody
    public String availableTimeByDate(@RequestParam HashMap<String, Object> obj) throws JsonMappingException, JsonProcessingException {
        String processingDate = obj.get("processingDate").toString();
        String specialtyName = obj.get("specialtyName").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(processingDate, formatter);
        
        List<String> listOfTimes = commonServices.getAvailableTimeBySpecialtyNameAndDate(specialtyName, date);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = objectMapper.writeValueAsString(listOfTimes);
        return jsonArray;
    }

    @GetMapping(
        value = "/specialists/availableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt",
        produces = "application/json"
    )
    @ResponseBody
    public String availableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt(@RequestParam HashMap<String, String> map) throws JsonProcessingException {
        String specialtyName = map.get("specialtyName");
        String dateOfWorkString = map.get("dateOfWork");
        String workTimeStartAtString = map.get("workTimeStartAt");

        DateTimeFormatter formatterDate= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfWork = LocalDate.parse(dateOfWorkString, formatterDate);
        
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime workTimeStartAt = LocalTime.parse(workTimeStartAtString, formatterTime);

        Map<Integer, String> availableSpecialistsMap = commonServices.
        getAvailableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAtAndAvailability(specialtyName, dateOfWork,
                                                                                             workTimeStartAt);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAvailableSpecialists = objectMapper.writeValueAsString(availableSpecialistsMap);
        return jsonAvailableSpecialists;
    }

    @GetMapping("/specialists/{specialtyName}")
    public String bookAnAppointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);

        List<Date> availableDatesOfWork = commonServices.getDatesOfWorkBySpecialtyName(specialtyName);
        model.addAttribute("availableDatesOfWork", availableDatesOfWork);
        
        return "book_an_appointment";
    }

    @PatchMapping("/specialists/bookplace")
    public String bookPlace(@ModelAttribute("bookApointment") BookAppointment bookAppointment,
                            @RequestParam("dateOfWork") String bookingDateString,
                            @RequestParam("timeStartAt") String bookingTimeString,
                            @RequestParam("availableSpecialists") int specialistId,
                            Model model) {
        DateTimeFormatter formatterDate= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bookingDate = LocalDate.parse(bookingDateString, formatterDate);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime bookingTime = LocalTime.parse(bookingTimeString, formatterTime);

        commonServices.makeBookingBySpecialistIdAndBookingDateAndBookingTime(specialistId, bookingDate, bookingTime);
        String action = commonServices.getDefaultPageLinkCurrentUser();
        model.addAttribute("action", action);

        return "/successful_action_page";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
