package ru.khaimin.finalproject.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ru.khaimin.finalproject.entity.Patient;

public class PatientDetails implements UserDetails{
    private final Patient patient;
    

    public PatientDetails(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(patient.getRole()));
    }

    @Override
    public String getPassword() {        
        return this.patient.getPassword();
    }

    @Override
    public String getUsername() {        
        return this.patient.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Patient getPatient() {
        return this.patient;
    }
}
