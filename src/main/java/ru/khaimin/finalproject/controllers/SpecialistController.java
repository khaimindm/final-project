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

import ru.khaimin.finalproject.entity.AppointmentData;
import ru.khaimin.finalproject.entity.BookingList;
import ru.khaimin.finalproject.entity.MedicalCard;
import ru.khaimin.finalproject.entity.Person;
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
        value = "/bookingListByDate",
        produces = "application/json"
    )
    @ResponseBody
    public String bookingListByDate(@RequestParam HashMap<String, Object> obj) throws JsonProcessingException {
        String processingDate = obj.get("processingDate").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(processingDate, formatter);

        List<AppointmentData> appointmentData = specialistService.getAppointmentDataByDate(date);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(appointmentData);
    }

    @GetMapping("/appointment/{bookingListId}")
    public String appointment(@PathVariable("bookingListId") int bookingListId, Model model,
    @ModelAttribute("medicalCard") MedicalCard medicalCard) {
        Person patient = commonServices.getBookingListByBookingListId(bookingListId).getPerson();
        String patientName = patient.getFirstName() + " " + patient.getLastName();
        model.addAttribute("patientName", patientName);
        model.addAttribute("bookingListId", bookingListId);
        
        return "medical_record";
    }

    @PostMapping("/appointment")
    public String addDetailsOfDoctorsAppointment(@ModelAttribute("medicalCard") MedicalCard medicalCard,
    Model model) {
        medicalCard.setSpecialistId(currentPerson().getId());
        LocalDate currentDate = LocalDate.now();
        medicalCard.setDateOfAppointment(currentDate);

        BookingList bookingList = commonServices.getBookingListByBookingListId(medicalCard.getBookingListId());
        bookingList.setCompleted(true);
        specialistService.saveBookingList(bookingList);

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
