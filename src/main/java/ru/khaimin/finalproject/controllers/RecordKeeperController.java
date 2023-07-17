package ru.khaimin.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecordKeeperController {    
    @GetMapping("/main_record_keeper")
    public String mainRecordKeeper() {
        return "main_record_keeper";
    }
}
