package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.finalproject.repositories.CommonRepository;

import java.util.List;

@Service
public class CommonServices {
    private final CommonRepository commonRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

public List<> getListOfSpecialties() {
}
}
