package com.id3.notebookprofile.notebookprofile.service.profile.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Profile {

    public static final String PROFILE_SEQ = "PROFILE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROFILE_SEQ)
    @SequenceGenerator(name = PROFILE_SEQ, sequenceName = "PROFILE_SEQ", allocationSize = 1)
    private int profileId;
    private String keycloakId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
