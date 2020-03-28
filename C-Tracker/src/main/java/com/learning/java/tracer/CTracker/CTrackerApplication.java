package com.learning.java.tracer.CTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.learning.java.repository"})
@EntityScan(basePackages= {"com.learning.java.models"})
@EnableScheduling
@ComponentScan(basePackages= {"com.learning.java.services", "com.learning.java.controllers", "com.learning.java.repository"})
public class CTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CTrackerApplication.class, args);
	}

}
