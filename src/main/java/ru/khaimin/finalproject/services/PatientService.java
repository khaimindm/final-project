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
    //private final SessionFactory sessionFactory;
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

    public List<String> getAvailableTimeBySpecialtyNameAndDate(String specialtyName, LocalDate date) {
        Iterator<Time> iterator = workTimeRepository.findBySpecialtyNameAndDateOfWorkAndAvailability
                (specialtyName, date, true).iterator();
        List<String> availableTimes = new ArrayList<>();
        while (iterator.hasNext()) {
            availableTimes.add(iterator.next().toLocalTime().toString());
        }

        return availableTimes;
    }

    public Map<Integer, String> getAvailableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAtAndAvailability
            (String specialtyName, LocalDate date, LocalTime time) {
        Iterator<WorkTime> iterator = workTimeRepository.
                findBySpecialtyNameAndDateOfWorkAndWorkTimeStartAtAndAvailability(specialtyName, date, time,
                        true).iterator();
        Map<Integer, String> availableSpecialistsMap = new HashMap<>();
        while (iterator.hasNext()) {
            WorkTime elWorkTime = iterator.next();
            Person person = elWorkTime.getPerson();
            availableSpecialistsMap.put(person.getId(), person.getLastName() + " " + person.getFirstName());
        }

        return availableSpecialistsMap;
    }

    public List<java.sql.Date> getDatesOfWorkBySpecialtyName(String specialtyName) {
        LocalDate currentDate = LocalDate.now();
        java.sql.Date date = java.sql.Date.valueOf(currentDate);
        System.out.println(currentDate);
        Iterator<java.sql.Date> iterator = workTimeRepository.findBySpecialtyNameAndAvailabilityAndDateOfWork(
                specialtyName, true, date).iterator();
        List<Date> availableDatesOfWork = new ArrayList<>();
        while (iterator.hasNext()) {
            availableDatesOfWork.add(iterator.next());
        }

        return availableDatesOfWork;
    }

    @Transactional
    public WorkTime makeBookingBySpecialistIdAndBookingDateAndBookingTime (int specialistId, LocalDate bookingDate,
                                                                           LocalTime bookingTime) {
        
        WorkTime workTime = workTimeRepository.findByPersonsPersonIdAndDateOfWorkAndWorkTimeStartAtAndAvailability(
                specialistId, bookingDate, bookingTime, true).orElse(new WorkTime());
        workTime.setAvailability(false);
        
        return workTime;
    }

    @Transactional
    public void makeBooking(BookingList bookingList, WorkTime workTime) {
        Person person = commonServices.getCurrentUser();
        bookingList.setPerson(person);
        bookingList.setWorkTime(workTime);
        bookingListRepository.save(bookingList);
    }

}
