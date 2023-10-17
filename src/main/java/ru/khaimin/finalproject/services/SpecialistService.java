package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.PatientList;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SpecialistService {
    private final WorkTimeRepository workTimeRepository;
    private final CommonServices commonServices;

    @Autowired
    public SpecialistService(WorkTimeRepository workTimeRepository, CommonServices commonServices) {
        this.workTimeRepository = workTimeRepository;
        this.commonServices = commonServices;
    }

    public List<PatientList> getPatientListByDate(LocalDate date) {
        Iterator<WorkTime> iterator = workTimeRepository.findByDateOfWorkAndAvailabilityAndPersonsPersonId(date, false,
        commonServices.getCurrentUser().getId()).iterator();
        List<PatientList> patientLists = new ArrayList<>();
        while (iterator.hasNext()) {
            PatientList patient = new PatientList();
            WorkTime el = iterator.next();
            patient.setPatientId(el.getBookingList().getPerson().getId());
            patient.setFirstName(el.getBookingList().getPerson().getFirstName());
            patient.setLastName(el.getBookingList().getPerson().getLastName());
            patient.setTime(el.getWorkTimeStartAt().toString());            
            patientLists.add(patient);
        }

        return patientLists;
    }
}