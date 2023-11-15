package ru.khaimin.finalproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.khaimin.finalproject.entity.*;
import ru.khaimin.finalproject.services.CommonServices;
import ru.khaimin.finalproject.services.PeopleService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommonActionsController {

    private final CommonServices commonServices;
    private final PeopleService peopleService;
    
    @Autowired
    public CommonActionsController(CommonServices commonServices, PeopleService peopleService) {
        this.commonServices = commonServices;
        this.peopleService = peopleService;
    }

    @GetMapping("/specialists/{specialtyName}")
    public String bookAnAppointment(@PathVariable("specialtyName") String specialtyName, Model model) {
        model.addAttribute("specialtyName", specialtyName);

        List<Date> availableDatesOfWork = commonServices.getDatesOfWorkBySpecialtyName(specialtyName);
        model.addAttribute("availableDatesOfWork", availableDatesOfWork);

        return "book_an_appointment";
    }

    @PatchMapping("/specialists/bookplace")
    public String bookPlace(@ModelAttribute BookingList bookingList,
                            @RequestParam("dateOfWork") String bookingDateString,
                            @RequestParam("timeStartAt") String bookingTimeString,
                            @RequestParam("availableSpecialists") int specialistId,
                            @RequestParam("patient") int patientId,
                            Model model) {
        DateTimeFormatter formatterDate= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bookingDate = LocalDate.parse(bookingDateString, formatterDate);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime bookingTime = LocalTime.parse(bookingTimeString, formatterTime);

        WorkTime workTime = commonServices.makeBookingBySpecialistIdAndBookingDateAndBookingTime(specialistId,
                bookingDate, bookingTime);

        commonServices.makeBooking(bookingList, workTime, patientId);

        String action = commonServices.getDefaultPageLinkCurrentUser();
        model.addAttribute("action", action);

        return "/successful_action_page";
    }

    @GetMapping(
            value = "/specialists/availableTimeByDate",
            produces = "application/json"
    )
    @ResponseBody
    public String availableTimeByDate(@RequestParam HashMap<String, Object> obj) throws JsonMappingException,
            JsonProcessingException {
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
    public String availableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt(@RequestParam HashMap<String,
            String> map) throws JsonProcessingException {
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

        return objectMapper.writeValueAsString(availableSpecialistsMap);
    }

    @GetMapping(value = "/patients/all", produces = "application/json")
    @ResponseBody
    public String allPatientsAjax() throws JsonProcessingException {
        Map<Integer, String> allPatientsMap = commonServices.getAllPatients();

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(allPatientsMap);
    }

    @GetMapping("/all_patients")
    public String allPatients(Model model) {
        List<Person> allPatients = peopleService.getAllByRole("ROLE_PATIENT");
        model.addAttribute("patients", allPatients);

        return "list_of_patients";
    }

    @GetMapping("/patient/{id}")
    public String patientData(@PathVariable("id") int id, Model model) {
        Person patient = commonServices.getPersonById(id).orElse(new Person());
        if (!patient.getRole().equals("ROLE_PATIENT")) {
            return "invalid_page";
        }

        model.addAttribute("patient", patient);
        List<FutureAppointment> futurePatientAppointments = commonServices.getFuturePatientAppointmentsByPatientId(id);
        model.addAttribute("futurePatientAppointments", futurePatientAppointments);

        List<AppointmentBook> appointmentBooks = commonServices.getAllAppointmentByPatientId(id);
        model.addAttribute("appointmentBooks", appointmentBooks);

        return "patient_data";
    }

    @GetMapping("/patient/medical_card/{id}")
    public String medicalCard(@PathVariable("id") int id, Model model) {
        MedicalCard medicalCard = commonServices.getMedicalCardByMedicalCardId(id);
        model.addAttribute("medicalCard", medicalCard);

        Person patient = commonServices.getBookingListByBookingListId(medicalCard.getBookingListId()).getPerson();
        Person specialist = commonServices.getPersonById(medicalCard.getSpecialistId()).orElse(new Person());

        model.addAttribute("patientsFirstName", patient.getFirstName());
        model.addAttribute("patientsLastName", patient.getLastName());
        model.addAttribute("specialistsFirstName", specialist.getFirstName());
        model.addAttribute("specialistsLastName", specialist.getLastName());

        String specialtyName = commonServices.getProfessionalActivityByPersonsPersonId(medicalCard.getSpecialistId()).
                getSpecialtyName();
        model.addAttribute("specialtyName", specialtyName);

        return "medical_card";
    }

    @ModelAttribute("currentUser")
    public Person currentPerson() {
        return commonServices.getCurrentUser();
    }
}
