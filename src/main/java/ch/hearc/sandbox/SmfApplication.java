package ch.hearc.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SmfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmfApplication.class, args);
	}

}
