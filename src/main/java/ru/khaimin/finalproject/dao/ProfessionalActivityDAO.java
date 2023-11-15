package ru.khaimin.finalproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.khaimin.finalproject.entity.ProfessionalActivity;

@Component
public class ProfessionalActivityDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfessionalActivityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProfessionalActivity getProfessionalActivityByPersonsPersonId(int specialistId) {
        return jdbcTemplate.query("SELECT * FROM professional_activity WHERE persons_person_id=?",
                new Object[]{specialistId}, new BeanPropertyRowMapper<>(ProfessionalActivity.class)).stream().
                findAny().orElse(null);
    }
}
