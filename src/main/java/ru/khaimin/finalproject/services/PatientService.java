package ru.khaimin.finalproject.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

@Service
public class PatientService {
    private final WorkTimeRepository workTimeRepository;

    @Autowired
    public PatientService(WorkTimeRepository workTimeRepository) {
        this.workTimeRepository = workTimeRepository;
    }
    
}
