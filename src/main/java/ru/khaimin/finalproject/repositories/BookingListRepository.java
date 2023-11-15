package ru.khaimin.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.khaimin.finalproject.entity.BookingList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingListRepository extends JpaRepository<BookingList, Integer>{
    @Query(value = "SELECT * FROM booking_list WHERE persons_person_id = :personsPersonId", nativeQuery = true)
    List<BookingList> findAllByPersonsPersonId(@Param("personsPersonId") int id);
}
