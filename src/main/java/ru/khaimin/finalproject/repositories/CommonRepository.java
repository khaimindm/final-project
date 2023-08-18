package ru.khaimin.finalproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khaimin.finalproject.entity.ProfessionalActivity;

@Repository
public interface CommonRepository extends JpaRepository<ProfessionalActivity, Integer> {

    @Query("SELECT DISTINCT specialtyName FROM ProfessionalActivity ORDER BY specialtyName")
    List<String> getListOfSpecialties();
}
