package com.example.test;

import com.example.test.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

    @Bean(name = "_UserBean")
	public User setUser(){
		User u=new User();
		u.setNomUtilisateur("nouha");
		u.setPassword("nouha2001");
		return u;
	}
}
