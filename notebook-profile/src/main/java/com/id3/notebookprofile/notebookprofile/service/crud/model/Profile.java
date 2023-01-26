package com.id3.notebookprofile.notebookprofile.service.crud.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int profileId;
    private String keycloakId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
