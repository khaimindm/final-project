package ru.khaimin.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.khaimin.finalproject.security.UrlAuthenticationSuccessHandler;
import ru.khaimin.finalproject.services.PersonDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandler();
    }

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/main_record_keeper", "/auth/registration_record_keeper", "/adding_specialist_data",
                "/appointment", "/list_of_specialists", "/work_time", "/all_tables").hasRole("RECORDKEEPER")
        .antMatchers("/main_specialist", "/bookingListByDate", "/appointment/*", "/appointment",
                "/all_patients", "/patient/medical_card/*").hasRole("SPECIALIST")
        .antMatchers("/main_patient").hasRole("PATIENT")
        .antMatchers("/auth/login", "/auth/registration", "/error", "/css/**", "/js/**",
                "/successful_action_page", "/invalid_page").permitAll()
                .antMatchers("/specialists/*", "/patients/all").hasAnyRole("RECORDKEEPER","PATIENT")
        .and()
        .formLogin().loginPage("/auth/login")
        .loginProcessingUrl("/process_login")
        .successHandler(authenticationSuccessHandler())
        .failureUrl("/auth/login?error")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/auth/login");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
