package com.id3.notebookcrud.service.crud.model;


import lombok.Data;

@Data
public class NotebookDTO {
    String keycloakId;
    String notebookContent;
    boolean scheduled;
    String scheduleCron;
}
