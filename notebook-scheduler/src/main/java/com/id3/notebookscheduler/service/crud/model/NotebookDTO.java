package com.id3.notebookscheduler.service.crud.model;

import lombok.Data;

@Data
public class NotebookDTO {
    String keycloakId;
    String notebookContent;
    boolean scheduled;
    String scheduleCron;
}