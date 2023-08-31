package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.DataForWorkTime;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.ProfessionalActivityRepository;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class RecordKeeperService {
    private final PeopleRepository peopleRepository;
    private final WorkTimeRepository workTimeRepository;

    @Autowired
    public RecordKeeperService(PeopleRepository peopleRepository, WorkTimeRepository workTimeRepository) {
        this.peopleRepository = peopleRepository;
        this.workTimeRepository = workTimeRepository;
    }

    public Optional<Person> getPersonById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson;
    }

    public void workTime(Person person, DataForWorkTime dataForWorkTime) {

        if (dataForWorkTime.getWorkTimeMorning()) {
            LocalTime workTimeStartAt = LocalTime.of(8, 0);
            LocalTime workTimeEndAt = LocalTime.of(12, 0);


            for(LocalTime time = workTimeStartAt; time.isAfter(workTimeEndAt); time.plusMinutes(dataForWorkTime.getAppointmentInterval())) {
                WorkTime workTime = new WorkTime();
                workTime.setDateOfWork(dataForWorkTime.getDateOfWork());
                workTime.setWorkTimeStartAt(time);
                time = time.plusMinutes(dataForWorkTime.getAppointmentInterval());
                workTime.setWorkTimeEndAt(time);
                time = time.minusMinutes(dataForWorkTime.getAppointmentInterval());
                workTime.setAvailability(true);
                workTime.setPerson(person);
                workTimeRepository.save(workTime);
            }
            
        }
    }
}
