package ru.khaimin.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.entity.ProfessionalActivity;

import java.util.Optional;

@Repository
public interface ProfessionalActivityRepository extends JpaRepository<ProfessionalActivity, Integer> {
    Optional<ProfessionalActivity> findByPerson(Person person);

    @Query(value = "SELECT * FROM professional_activity WHERE persons_person_id = :personsPersonId", nativeQuery = true)
    Optional<ProfessionalActivity> findByPersonsPersonId(@Param("personsPersonId") int id);
    
}
