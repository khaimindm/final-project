package ru.khaimin.finalproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        jdbcTemplate.update("INSERT INTO medical_cards (medical_card_id, patient_id, examination, diagnosis, assigned_therapy, date_of_appointment, specialist_id) VALUES(1, ?, ?, ?, ?, ?, ?)", 
        medicalCard.getPatientId(),
        medicalCard.getExamination(), 
        medicalCard.getDiagnosis(), 
        medicalCard.getAssignedTherapy(),
        medicalCard.getDateOfAppointment(), 
        medicalCard.getSpecialistId()
        );
    }
    
}
