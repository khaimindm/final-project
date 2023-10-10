package ru.khaimin.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.finalproject.entity.BookAppointment;
import ru.khaimin.finalproject.entity.Person;
import ru.khaimin.finalproject.repositories.CommonRepository;
import ru.khaimin.finalproject.security.PersonDetails;

import java.util.Collection;
import java.util.List;

@Service
public class CommonServices {
    
    private final CommonRepository commonRepository;

    @Autowired
    public CommonServices(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    public List<String> loadSpecialties() {
        return commonRepository.getListOfSpecialties();
    }

    public Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

    public String getDefaultPageLinkCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String authorityName = "";
        for (GrantedAuthority grantedAuthority: authorities) {
            authorityName = grantedAuthority.getAuthority();
        }

        if (authorityName.equalsIgnoreCase("ROLE_RECORDKEEPER")) {
            return "/main_record_keeper";
        } else if (authorityName.equalsIgnoreCase("ROLE_SPECIALIST")) {
            return "/main_specialist";
        } else {
            return "/main_patient";
        }
    }

    @Transactional
    public void performRecording(BookAppointment bookAppointment) {
        bookAppointment.setPerson(getCurrentUser());
        //bookAppointment.setWorkTime(workTime);
    }

}
