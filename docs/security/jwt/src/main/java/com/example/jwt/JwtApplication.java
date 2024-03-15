package com.example.jwt;

import com.example.jwt.customuser.CustomUser;
import com.example.jwt.customuser.MapCustomUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}


	@Bean
	MapCustomUserRepository userRepository() {
		// the raw password is "password"
		String encodedPassword = "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm";

		CustomUser customUser = new CustomUser(1L, "user@example.com", encodedPassword);
		Map<String, CustomUser> emailToCustomUser = new HashMap<>();
		emailToCustomUser.put(customUser.getEmail(), customUser);
		return new MapCustomUserRepository(emailToCustomUser);
	}
}
