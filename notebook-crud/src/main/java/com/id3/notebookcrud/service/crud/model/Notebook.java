package com.id3.notebookcrud.service.crud.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notebookId;
    private String keycloakId;
    private String notebookContent;
    private LocalDateTime createdOn;
    private boolean scheduled;
    private String scheduleCron;
    private boolean active;
}
