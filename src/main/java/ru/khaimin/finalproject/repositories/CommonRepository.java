package ru.khaimin.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khaimin.finalproject.entity.ProfessionalActivity;

@Repository
public interface CommonRepository extends JpaRepository<ProfessionalActivity, Integer> {
}
