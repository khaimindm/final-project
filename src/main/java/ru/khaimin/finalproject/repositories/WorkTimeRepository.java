package ru.khaimin.finalproject.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.WorkTime;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer>{
    List<WorkTime> findByDateOfWorkAndAvailability(LocalDate date, boolean availability);
}
