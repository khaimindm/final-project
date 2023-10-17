package ru.khaimin.finalproject.repositories;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.WorkTime;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer>{
    @Query(value = "SELECT DISTINCT date_of_work FROM work_time WHERE specialty_name = :specialtyName AND" +
    " availability = :availability AND date_of_work >= :currentDate ORDER BY date_of_work", nativeQuery = true)
    List<Date> findBySpecialtyNameAndAvailabilityAndDateOfWork(
        @Param("specialtyName") String specialtyName,
        @Param("availability") Boolean availability,
        @Param("currentDate") Date currentDate
    );

    @Query(value = "SELECT DISTINCT work_time_start_at FROM work_time WHERE specialty_name = :specialtyName AND" +
            " date_of_work = :date AND availability =:availability ORDER BY work_time_start_at", nativeQuery = true)
    List<Time> findBySpecialtyNameAndDateOfWorkAndAvailability(
    @Param("specialtyName") String specialtyName, 
    @Param("date") LocalDate date, 
    @Param("availability") boolean availability);

    List<WorkTime> findBySpecialtyNameAndDateOfWorkAndWorkTimeStartAtAndAvailability(String specialtyName,
                                                                                     LocalDate dateOfWork,
                                                                                     LocalTime workTimeStartAt,
                                                                                     boolean availability);

    List<WorkTime> findBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt(String specialtyName, LocalDate dateOfWork,
                                                                      LocalTime workTimeStartAt);
    
    List<WorkTime> findBySpecialtyNameAndDateOfWork(String specialtyName, LocalDate dateOfWork);

    /*Optional<WorkTime> findByPersonsPersonIdAndDateOfWorkAndWorkTimeStartAtAndAvailability(int personsPersonId,
                                                                                           LocalDate dateOfWork,
                                                                                           LocalTime workTimeStartAt,
                                                                                           boolean availability);*/

    /*Optional<WorkTime> findByPersonsPersonIdAndDateOfWorkAndWorkTimeStartAtAndAvailability(int personsPersonId,
                                                                                           LocalDate dateOfWork,
                                                                                           LocalTime workTimeStartAt,
                                                                                           boolean availability);*/

    @Query(value = "SELECT * FROM work_time WHERE persons_person_id = :personsPersonId AND " +
            "date_of_work = :dateOfWork AND work_time_start_at = :workTimeStartAt AND availability = :availability",
            nativeQuery = true)
    Optional<WorkTime> findByPersonsPersonIdAndDateOfWorkAndWorkTimeStartAtAndAvailability(
            @Param("personsPersonId") int personsPersonId,
            @Param("dateOfWork") LocalDate dateOfWork,
            @Param("workTimeStartAt") LocalTime workTimeStartAt,
            @Param("availability") boolean availability);


        @Query(value = "SELECT * FROM work_time WHERE date_of_work = :dateOfWork AND availability = :availability AND persons_person_id = :personsPersonId", nativeQuery = true)
        List<WorkTime> findByDateOfWorkAndAvailabilityAndPersonsPersonId(
                @Param("dateOfWork") LocalDate dateOfWork, 
                @Param("availability") boolean availability, 
                @Param("personsPersonId") int personsPersonId);
}
