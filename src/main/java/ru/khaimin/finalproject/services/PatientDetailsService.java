package ru.khaimin.finalproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.khaimin.finalproject.entity.Patient;
import ru.khaimin.finalproject.repositories.PatientRepository;
import ru.khaimin.finalproject.security.PatientDetails;

@Service
public class PatientDetailsService implements UserDetailsService{
    private final PatientRepository patientRepository;

    @Autowired
    public PatientDetailsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Patient> patient = patientRepository.findByUsername(username);

        if (patient.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        return new PatientDetails(patient.get());
    }
    
}
