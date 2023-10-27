package ru.khaimin.finalproject.dao;

import java.util.List;

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
        jdbcTemplate.update("INSERT INTO medical_cards (patient_id, examination, diagnosis, assigned_therapy, date_of_appointment, specialist_id) VALUES(?, ?, ?, ?, ?, ?)", 
        medicalCard.getPatientId(),
        medicalCard.getExamination(), 
        medicalCard.getDiagnosis(), 
        medicalCard.getAssignedTherapy(),
        medicalCard.getDateOfAppointment(), 
        medicalCard.getSpecialistId()
        );
    }

    public List<MedicalCard> allMedicalCards() {
        return jdbcTemplate.query("SELECT * FROM medical_cards", new BeanPropertyRowMapper<>(MedicalCard.class));
    }
    
}
