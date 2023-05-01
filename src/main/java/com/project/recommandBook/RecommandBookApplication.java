package com.project.recommandBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecommandBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommandBookApplication.class, args);
	}

}
