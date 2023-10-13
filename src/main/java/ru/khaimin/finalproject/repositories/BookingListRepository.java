package ru.khaimin.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.khaimin.finalproject.entity.BookingList;

public interface BookingListRepository extends JpaRepository<BookingList, Integer>{
    
}
