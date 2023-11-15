package ru.khaimin.finalproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.khaimin.finalproject.entity.MedicalCard;

@Component
public class MedicalCardDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicalCardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void saveDetailsOfDoctorsAppointment(MedicalCard medicalCard) {
        jdbcTemplate.update("INSERT INTO medical_cards (examination, diagnosis, assigned_therapy, " +
                        "date_of_appointment, specialist_id, booking_list_id) VALUES(?, ?, ?, ?, ?, ?)",
                medicalCard.getExamination(),
                medicalCard.getDiagnosis(),
                medicalCard.getAssignedTherapy(),
                medicalCard.getDateOfAppointment(),
                medicalCard.getSpecialistId(),
                medicalCard.getBookingListId()
        );
    }

    public List<MedicalCard> allMedicalCards() {
        return jdbcTemplate.query("SELECT * FROM medical_cards", new BeanPropertyRowMapper<>(MedicalCard.class));
    }

    public List<MedicalCard> medicalCardsByPatientId(int patientId) {
        return jdbcTemplate.query("SELECT * " +
                        "FROM medical_cards " +
                        "INNER JOIN booking_list ON medical_cards.booking_list_id = booking_list.booking_list_id " +
                        "WHERE booking_list.persons_person_id=?",
                new Object[]{patientId},
                new BeanPropertyRowMapper<>(MedicalCard.class));
    }

    public MedicalCard medicalCardByMedicalCardId(int medicalCardId) {
        return jdbcTemplate.query("SELECT * FROM medical_cards WHERE medical_card_id=?", new Object[]{medicalCardId},
                new BeanPropertyRowMapper<>(MedicalCard.class)).stream().findAny().orElse(null);
    }
    
}
