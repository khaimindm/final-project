package ru.khaimin.finalproject.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ru.khaimin.finalproject.entity.BookAppointment;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

@Service
public class PatientService {
    private final WorkTimeRepository workTimeRepository;
    private final CommonServices commonServices;

    @Autowired
    public PatientService(WorkTimeRepository workTimeRepository, CommonServices commonServices) {
        this.workTimeRepository = workTimeRepository;
        this.commonServices = commonServices;
    }

    
    public WorkTime makeBookingBySpecialistIdAndBookingDateAndBookingTime (int specialistId, LocalDate bookingDate,
                                                                       LocalTime bookingTime) {
        
        WorkTime workTime = workTimeRepository.findByPersonsPersonIdAndDateOfWorkAndWorkTimeStartAtAndAvailability(
                specialistId, bookingDate, bookingTime, true).orElse(new WorkTime());

        workTime.setAvailability(false);
        
        return workTime;
    }
}
