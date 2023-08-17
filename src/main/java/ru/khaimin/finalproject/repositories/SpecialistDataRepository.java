package ru.khaimin.finalproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.ProfessionalActivity;

@Repository
public interface SpecialistDataRepository extends JpaRepository<ProfessionalActivity, Integer> {    
}
