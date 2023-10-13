package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.entity.*;
import ru.khaimin.finalproject.repositories.BookingListRepository;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.ProfessionalActivityRepository;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecordKeeperService {
    private final PeopleRepository peopleRepository;
    private final WorkTimeRepository workTimeRepository;
    private final ProfessionalActivityRepository professionalActivityRepository;
    private final BookingListRepository bookingListRepository;

    @Autowired
    public RecordKeeperService(PeopleRepository peopleRepository, WorkTimeRepository workTimeRepository,
                               ProfessionalActivityRepository professionalActivityRepository,
                               BookingListRepository bookingListRepository) {
        this.peopleRepository = peopleRepository;
        this.workTimeRepository = workTimeRepository;
        this.professionalActivityRepository = professionalActivityRepository;
        this.bookingListRepository = bookingListRepository;
    }

    public Optional<Person> getPersonById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson;
    }

    public void workTime(Person person, DataForWorkTime dataForWorkTime) {
         String specialtyName = professionalActivityRepository.findByPerson(person).orElse(new ProfessionalActivity()).getSpecialtyName();

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
                workTime.setSpecialtyName(specialtyName);
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
                workTime.setSpecialtyName(specialtyName);
                workTime.setPerson(person);
                workTimes.add(workTime);
            }

            workTimeRepository.saveAll(workTimes);
        }
    }

    public List<Person> getAllPersons() {
        return peopleRepository.findAll();
    }

    public List<ProfessionalActivity> getAllProfessionalActivity() {
        return professionalActivityRepository.findAll();
    }

    public List<WorkTime> getAllWorkTime() {
        return workTimeRepository.findAll();
    }
    
    public List<BookingList> getAllBookingList() {
        return bookingListRepository.findAll();
    }
}
