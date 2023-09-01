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
import java.util.ArrayList;
import java.util.List;
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
            List<WorkTime> workTimes = new ArrayList<>();
            LocalTime time = workTimeStartAt;

            for(int i = 0; i < 240; i = i + dataForWorkTime.getAppointmentInterval()) {                
                WorkTime workTime = new WorkTime();
                workTime.setDateOfWork(dataForWorkTime.getDateOfWork());
                workTime.setWorkTimeStartAt(time);
                time = time.plusMinutes(dataForWorkTime.getAppointmentInterval());
                workTime.setWorkTimeEndAt(time);
                workTime.setAvailability(true);
                workTime.setPerson(person);
                workTimes.add(workTime);
            }

            workTimeRepository.saveAll(workTimes);
        }

        if (dataForWorkTime.getWorkTimeAfternoon()) {
            LocalTime workTimeStartAt = LocalTime.of(13, 0);
            List<WorkTime> workTimes = new ArrayList<>();
            LocalTime time = workTimeStartAt;

            for (int i =0; i < 240; i = i + dataForWorkTime.getAppointmentInterval()) {
                WorkTime workTime = new WorkTime();
                workTime.setDateOfWork(dataForWorkTime.getDateOfWork());
                workTime.setWorkTimeStartAt(time);
                System.out.println("Work time start at: " + time);
                time = time.plusMinutes(dataForWorkTime.getAppointmentInterval());
                workTime.setWorkTimeEndAt(time);
                System.out.println("Work time end at: " + time);
                workTime.setAvailability(true);
                workTime.setPerson(person);
                workTimes.add(workTime);
            }

            workTimeRepository.saveAll(workTimes);
        }
    }
}
