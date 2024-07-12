package com.id3.notebookscheduler.service.crud.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotebookDTO {
    String keycloakId;
    String notebookContent;
    String createdOn;
    boolean scheduled;
    String scheduleCron;
}