package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

import java.time.LocalDate;
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

    public List<WorkTime> getPatientListByDate(LocalDate date) {
         return workTimeRepository.findByDateOfWorkAndAvailabilityAndPersonsPersonId(date, false,
                commonServices.getCurrentUser().getId());
    }
}
