package com.companion.transactions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TransactionsApplication {

	public static void main(String[] args) {

        System.out.println("Starting Transactions Application...");
        SpringApplication.run(TransactionsApplication.class, args);
	}

	@Bean
	CommandLineRunner printEnvironmentVariables(Environment env) {
		return args -> {
			System.out.println("\n========================================");
			System.out.println("ENVIRONMENT VARIABLES");
			System.out.println("========================================");

			// Afficher les propriétés Spring importantes
			System.out.println("spring.application.name: " + env.getProperty("spring.application.name"));
			System.out.println("spring.profiles.active: " + env.getProperty("spring.profiles.active"));
			System.out.println("server.port: " + env.getProperty("server.port"));

			// Database
			System.out.println("\n--- Database ---");
			System.out.println("spring.datasource.url: " + env.getProperty("spring.datasource.url"));
			System.out.println("spring.datasource.username: " + env.getProperty("spring.datasource.username"));
			System.out.println("spring.datasource.password: " + maskPassword(env.getProperty("spring.datasource.password")));

			// Kafka
			System.out.println("\n--- Kafka ---");
			System.out.println("spring.kafka.bootstrap-servers: " + env.getProperty("spring.kafka.bootstrap-servers"));

			// Stripe
			System.out.println("\n--- Stripe ---");
			System.out.println("stripe.api.key: " + maskPassword(env.getProperty("stripe.api.key")));
			System.out.println("stripe.webhook.secret: " + maskPassword(env.getProperty("stripe.webhook.secret")));

			System.out.println("========================================\n");
		};
	}

	private String maskPassword(String value) {
		if (value == null || value.isEmpty()) {
			return "NOT_SET";
		}
		if (value.length() <= 4) {
			return "****";
		}
		return value.substring(0, 4) + "****";
	}

}
