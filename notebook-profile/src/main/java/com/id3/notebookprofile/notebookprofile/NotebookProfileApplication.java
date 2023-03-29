package com.id3.notebookprofile.notebookprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class NotebookProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookProfileApplication.class, args);
	}

}
