package com.trinity.digitalEntryPass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@SpringBootApplication
public class DigitalEntryPassApplication{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountInfoMongoRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DigitalEntryPassApplication.class, args);
		System.out.println("yoyoyo");
	}
}
