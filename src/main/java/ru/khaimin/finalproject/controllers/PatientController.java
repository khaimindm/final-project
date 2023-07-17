package ru.khaimin.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {
    @GetMapping("/main_patient")
    public String mainPatient() {
        return "main_patient";
    }
}
