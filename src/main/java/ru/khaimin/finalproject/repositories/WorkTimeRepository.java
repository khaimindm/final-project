package ru.khaimin.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.khaimin.finalproject.entity.WorkTime;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer>{
    
}
