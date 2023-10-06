package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.finalproject.entity.BookAppointment;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.WorkTime;
import ru.khaimin.finalproject.repositories.CommonRepository;
import ru.khaimin.finalproject.repositories.PeopleRepository;
import ru.khaimin.finalproject.repositories.WorkTimeRepository;
import ru.khaimin.finalproject.security.PersonDetails;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class CommonServices {
    
    private final CommonRepository commonRepository;
    private final WorkTimeRepository workTimeRepository;

    private final PeopleRepository peopleRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository, WorkTimeRepository workTimeRepository,
                          PeopleRepository peopleRepository) {
        this.commonRepository = commonRepository;
        this.workTimeRepository = workTimeRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<String> loadSpecialties() {
        List<String> specialties = commonRepository.getListOfSpecialties();
        return specialties;
    }

    public List<Date> getDatesOfWorkBySpecialtyName(String specialtyName) {
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        System.out.println(currentDate);        
        Iterator<Date> iterator = workTimeRepository.findBySpecialtyNameAndAvailabilityAndDateOfWork(specialtyName, true, date).iterator();
        List<Date> availableDatesOfWork = new ArrayList<>();
        while (iterator.hasNext()) {
            availableDatesOfWork.add(iterator.next());
        }

        return availableDatesOfWork;
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
        //Optional<List<Time>> availableTimeBySpecialtyNameAndDate = workTimeRepository.findBySpecialtyNameAndDateOfWorkAndAvailability(specialtyName, date, true);

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

    /*public WorkTime getWorkTimeByPersonsPersonIdAndDateOfWorkAndWorkTimeStartAtAndAvailability(
            int personsPersonId, LocalDate dateOfWork, LocalTime workTimeStartAt) {
        return
    }*/

    /*public Person getPersonById(int id) {
        return peopleRepository.findById(id).orElse(new Person());
    }*/

    public String getDefaultPageLinkCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String authorityName = "";
        for (GrantedAuthority grantedAuthority: authorities) {
            authorityName = grantedAuthority.getAuthority();
        }

        if (authorityName.equalsIgnoreCase("ROLE_RECORDKEEPER")) {
            return "/main_record_keeper";
        } else if (authorityName.equalsIgnoreCase("ROLE_SPECIALIST")) {
            return "/main_specialist";
        } else {
            return "/main_patient";
        }
    }

    public void makeBooking(WorkTime workTime) {
        BookAppointment bookAppointment = new BookAppointment();
                bookAppointment.setPerson(getCurrentUser());
                bookAppointment.setWorkTime(workTime);
    }

    
}
