package com.stati.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.stati.cursomc.services.DBService;
import com.stati.cursomc.services.EmailService;
import com.stati.cursomc.services.SMTPEmialService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	//@Value("${spring.jpa.hibernate.ddl-auto}")
	//private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		//if(!"create".equals(strategy)) {
			//return false;
		//}
		
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SMTPEmialService();
	}
}
