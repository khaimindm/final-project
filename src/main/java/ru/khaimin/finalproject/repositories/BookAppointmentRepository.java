package ru.khaimin.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khaimin.finalproject.entity.BookAppointment;

public interface BookAppointmentRepository extends JpaRepository<BookAppointment, Integer> {

}
