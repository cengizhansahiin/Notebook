package com.id3.notebookscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class NotebookSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookSchedulerApplication.class, args);
	}

}
