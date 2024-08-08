package com.example.maids_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class MaidsProjectApplication implements CommandLineRunner {



	public static void main(String[] args) {

		SpringApplication.run(MaidsProjectApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

	}
}
