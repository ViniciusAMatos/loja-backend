package com.viniciusmatos.cursomc.config;

import com.viniciusmatos.cursomc.services.DBService;
import com.viniciusmatos.cursomc.services.EmailService;
import com.viniciusmatos.cursomc.services.MockEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
   private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();

        return  true;
    }
    
    @Bean
    public EmailService emailService() {
    	return new MockEmailService();
    }
}