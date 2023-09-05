package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.CommonRepository;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;
import ru.khaimin.finalproject.security.PersonDetails;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CommonServices {
    
    private final CommonRepository commonRepository;
    private final WorkTimeRepository workTimeRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository, WorkTimeRepository workTimeRepository) {
        this.commonRepository = commonRepository;
        this.workTimeRepository = workTimeRepository;
    }

    public List<String> loadSpecialties() {
        List<String> specialties = commonRepository.getListOfSpecialties();
        return specialties;
    }

    public Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
    
    public List<LocalTime> getAvailableTimeByDate(LocalDate date) {
        Iterator<WorkTime> iterator = workTimeRepository.findByDateOfWorkAndAvailability(date, true).iterator();
        List<LocalTime> availableTimes = new ArrayList<>();
        while (iterator.hasNext()) {
            availableTimes.add(iterator.next().getWorkTimeStartAt());
        }
        
        return availableTimes;
    }
}
