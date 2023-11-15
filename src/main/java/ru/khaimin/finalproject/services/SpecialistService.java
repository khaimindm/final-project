package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.finalproject.dao.MedicalCardDAO;
import ru.khaimin.finalproject.dao.PersonDAO;
import ru.khaimin.finalproject.entity.*;
import ru.khaimin.finalproject.repositories.BookingListRepository;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialistService {
    private final WorkTimeRepository workTimeRepository;
    private final CommonServices commonServices;
    private final PersonDAO personDAO;
    private final MedicalCardDAO medicalCardDAO;
    private final BookingListRepository bookingListRepository;

    @Autowired
    public SpecialistService(WorkTimeRepository workTimeRepository, CommonServices commonServices, PersonDAO personDAO,
                             MedicalCardDAO medicalCardDAO, BookingListRepository bookingListRepository) {
        this.workTimeRepository = workTimeRepository;
        this.commonServices = commonServices;
        this.personDAO = personDAO;
        this.medicalCardDAO = medicalCardDAO;
        this.bookingListRepository = bookingListRepository;
    }

    public List<AppointmentData> getAppointmentDataByDate(LocalDate date) {
        Iterator<WorkTime> iterator = workTimeRepository.findByDateOfWorkAndAvailabilityAndPersonsPersonId(date,
                false, commonServices.getCurrentUser().getId()).iterator();
        List<AppointmentData> appointmentDataList = new ArrayList<>();
        while (iterator.hasNext()) {
            WorkTime el = iterator.next();
            if (!el.getBookingList().isCompleted()) {
                AppointmentData appointmentData = new AppointmentData();
                appointmentData.setBookingListId(el.getBookingList().getBookingListId());
                appointmentData.setFirstName(el.getBookingList().getPerson().getFirstName());
                appointmentData.setLastName(el.getBookingList().getPerson().getLastName());
                appointmentData.setTime(el.getWorkTimeStartAt().toString());
                appointmentDataList.add(appointmentData);
            }
        }

        return appointmentDataList;
    }

    public Person personById(int id) { 
        return personDAO.getPersonById(id);       
    }

    public void detailsOfDoctorsAppointment(MedicalCard medicalCard) {
        medicalCardDAO.saveDetailsOfDoctorsAppointment(medicalCard);
    }

    @Transactional
    public void saveBookingList(BookingList bookingList) {
        bookingListRepository.save(bookingList);
    }
}
