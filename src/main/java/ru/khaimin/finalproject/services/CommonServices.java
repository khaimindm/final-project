package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.repositories.CommonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommonServices {
    public String nextAction;
    private final CommonRepository commonRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;        
    }

    public List<String> loadSpecialties() {
        List<String> specialties = commonRepository.getListOfSpecialties();
        return specialties;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }
    
}
