package ru.khaimin.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecialistController {
    @GetMapping("/main_specialist")
    public String mainSpecialist() {
        return "main_specialist";
    }
}
