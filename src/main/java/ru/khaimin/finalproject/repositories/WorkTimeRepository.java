package ru.khaimin.finalproject.repositories;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.WorkTime;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer>{
    //List<WorkTime> findDistinctByDateOfWorkAndAvailabilityAndSpecialtyName(LocalDate date, boolean availability, String specialtyName);
    @Query(value = "SELECT DISTINCT work_time_start_at FROM work_time WHERE specialty_name = :specialtyName AND date_of_work = :date AND availability =:availability ORDER BY work_time_start_at", nativeQuery = true)
    List<Time> findBySpecialtyNameAndDateOfWorkAndAvailability(
    @Param("specialtyName") String specialtyName, 
    @Param("date") LocalDate date, 
    @Param("availability") boolean availability);

    List<WorkTime> findBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt(String specialtyName, LocalDate dateOfWork,
                                                                      LocalTime workTimeStartAt);
    
    List<WorkTime> findBySpecialtyNameAndDateOfWork(String specialtyName, LocalDate dateOfWork);
}
