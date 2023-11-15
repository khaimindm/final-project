package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.finalproject.dao.MedicalCardDAO;
import ru.khaimin.finalproject.dao.PersonDAO;
import ru.khaimin.finalproject.dao.ProfessionalActivityDAO;
import ru.khaimin.finalproject.entity.*;
import ru.khaimin.finalproject.repositories.*;
import ru.khaimin.finalproject.security.PersonDetails;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class CommonServices {
    
    private final CommonRepository commonRepository;
    private final WorkTimeRepository workTimeRepository;
    private final PeopleRepository peopleRepository;
    private final BookingListRepository bookingListRepository;
    private final MedicalCardDAO medicalCardDAO;
    private final PersonDAO personDAO;
    private final ProfessionalActivityDAO professionalActivityDAO;
    private final ProfessionalActivityRepository professionalActivityRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository, WorkTimeRepository workTimeRepository,
                          PeopleRepository peopleRepository, BookingListRepository bookingListRepository,
                          MedicalCardDAO medicalCardDAO, PersonDAO personDAO,
                          ProfessionalActivityDAO professionalActivityDAO,
                          ProfessionalActivityRepository professionalActivityRepository) {
        this.commonRepository = commonRepository;
        this.workTimeRepository = workTimeRepository;
        this.peopleRepository = peopleRepository;
        this.bookingListRepository = bookingListRepository;
        this.medicalCardDAO = medicalCardDAO;
        this.personDAO = personDAO;
        this.professionalActivityDAO = professionalActivityDAO;
        this.professionalActivityRepository = professionalActivityRepository;
    }

    public List<String> loadSpecialties() {
        return commonRepository.getListOfSpecialties();
    }

    public Optional<Person> getPersonById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson;
    }

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

    public List<java.sql.Date> getDatesOfWorkBySpecialtyName(String specialtyName) {
        LocalDate currentDate = LocalDate.now();
        java.sql.Date date = java.sql.Date.valueOf(currentDate);
        System.out.println(currentDate);
        Iterator<Date> iterator = workTimeRepository.findBySpecialtyNameAndAvailabilityAndDateOfWork(
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
    public void makeBooking(BookingList bookingList, WorkTime workTime, int patientId) {
        Person person = getPersonById(patientId).orElse(new Person());
        bookingList.setPerson(person);
        bookingList.setWorkTime(workTime);
        bookingListRepository.save(bookingList);
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

    public Map<Integer, String> getAllPatients() {
        Iterator<Person> iterator = peopleRepository.findAllByRole("ROLE_PATIENT").iterator();
        Map<Integer, String> allPatientsMap = new HashMap<>();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            allPatientsMap.put(person.getId(), person.getLastName() + " " + person.getFirstName());
        }

        return allPatientsMap;
    }

    public List<FutureAppointment> getFuturePatientAppointmentsByPatientId(int patientId) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        LocalDateTime currentLocalDateTime = LocalDateTime.of(currentDate, currentTime);
        Iterator<BookingList> iterator = bookingListRepository.findAllByPersonsPersonId(patientId).iterator();

        List<FutureAppointment> futureAppointments = new ArrayList<>();
        while (iterator.hasNext()) {
            BookingList el = iterator.next();
            LocalDateTime elLocalDateTime = LocalDateTime.of(el.getWorkTime().getDateOfWork(), el.getWorkTime()
                                                             .getWorkTimeStartAt());

            if (elLocalDateTime.isAfter(currentLocalDateTime) & !el.isCompleted()) {
                FutureAppointment futureAppointment = new FutureAppointment();

                futureAppointment.setSpecialtyName(el.getWorkTime().getSpecialtyName());
                futureAppointment.setSpecialistsFirstName(el.getWorkTime().getPerson().getFirstName());
                futureAppointment.setSpecialistsLastName(el.getWorkTime().getPerson().getLastName());
                futureAppointment.setAppointmentDate(el.getWorkTime().getDateOfWork());
                futureAppointment.setAppointmentTime(el.getWorkTime().getWorkTimeStartAt());
                futureAppointments.add(futureAppointment);
            }
        }

        return futureAppointments;
    }

    public List<AppointmentBook> getAllAppointmentByPatientId(int patientId) {
        Iterator<MedicalCard> iterator = medicalCardDAO.medicalCardsByPatientId(patientId).iterator();

        List<AppointmentBook> appointmentBooks = new ArrayList<>();
        while (iterator.hasNext()) {
            MedicalCard el = iterator.next();
            AppointmentBook appointmentBook = new AppointmentBook();
            appointmentBook.setDateOfAppointment(el.getDateOfAppointment());

            Person specialist = peopleRepository.findById(el.getSpecialistId()).orElse(new Person());

            ProfessionalActivity professionalActivity = professionalActivityRepository.
                    findByPersonsPersonId(el.getSpecialistId()).orElse(new ProfessionalActivity());

            appointmentBook.setSpecialtyName(professionalActivity.getSpecialtyName());
            appointmentBook.setSpecialistsFirstName(specialist.getFirstName());
            appointmentBook.setSpecialistsLastName(specialist.getLastName());
            appointmentBook.setMedicalCardId(el.getMedicalCardId());
            appointmentBooks.add(appointmentBook);
        }

        return appointmentBooks;
    }

    public MedicalCard getMedicalCardByMedicalCardId(int medicalCardId) {
        return medicalCardDAO.medicalCardByMedicalCardId(medicalCardId);
    }

    public ProfessionalActivity getProfessionalActivityByPersonsPersonId(int personsPersonId) {
        return professionalActivityRepository.findByPersonsPersonId(personsPersonId).orElse(new ProfessionalActivity());
    }

    public BookingList getBookingListByBookingListId(int bookingListId) {
        return bookingListRepository.findById(bookingListId).orElse(new BookingList());
    }

    public Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

}
