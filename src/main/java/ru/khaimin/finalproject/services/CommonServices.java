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

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    public List<WorkTime> getAvailableWorkTimeBySpecialtyNameAndDateOfWork(String specialtyName, LocalDate dateOfWork) {
        return workTimeRepository.findBySpecialtyNameAndDateOfWork(specialtyName, dateOfWork);
    }

    public Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
    
    public List<String> getAvailableTimeBySpecialtyNameAndDate(String specialtyName, LocalDate date) {
        Iterator<Time> iterator = workTimeRepository.findBySpecialtyNameAndDateOfWorkAndAvailability(specialtyName, date, true).iterator();
        List<String> availableTimes = new ArrayList<>();
        while (iterator.hasNext()) {
            availableTimes.add(iterator.next().toLocalTime().toString());
        }
        
        return availableTimes;
    }

    public List<LocalTime> getAvailableTimes(List<WorkTime> workTimes) {
        Iterator<WorkTime> iterator = workTimes.iterator();
        List<LocalTime> availableTimes = new ArrayList<>();
        while (iterator.hasNext()) {
            availableTimes.add(iterator.next().getWorkTimeStartAt());
        }

        return availableTimes;
    }

    public List<WorkTime> getAvailableWorkTimeBySpecialtyNameAndDateAndTime(String specialtyName, LocalDate date,
                                                                            LocalTime time) {
        return workTimeRepository.findBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt(specialtyName, date, time);
    }
}
