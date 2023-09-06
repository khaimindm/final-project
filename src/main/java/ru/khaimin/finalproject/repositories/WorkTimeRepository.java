package ru.khaimin.finalproject.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.WorkTime;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer>{
    List<WorkTime> findByDateOfWorkAndAvailabilityAndSpecialtyName(LocalDate date, boolean availability,
                                                                   String specialtyName);

    List<WorkTime> findBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt(String specialtyName, LocalDate dateOfWork,
                                                                      LocalTime workTimeStartAt);
}
