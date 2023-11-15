package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.khaimin.finalproject.entity.BookingList;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.BookingListRepository;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class PatientService {
    private final TransactionTemplate transactionTemplate;
    private final WorkTimeRepository workTimeRepository;
    private final CommonServices commonServices;

    private final BookingListRepository bookingListRepository;

    @Autowired
    public PatientService(WorkTimeRepository workTimeRepository, CommonServices commonServices,
                          PlatformTransactionManager transactionManager,
                          BookingListRepository bookingListRepository) {
        this.workTimeRepository = workTimeRepository;
        this.commonServices = commonServices;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.bookingListRepository = bookingListRepository;
    }

}
