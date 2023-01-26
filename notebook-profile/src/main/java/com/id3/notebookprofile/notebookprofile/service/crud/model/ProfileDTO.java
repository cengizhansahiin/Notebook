package com.id3.notebookprofile.notebookprofile.service.crud.model;

import lombok.Data;

@Data
public class ProfileDTO {
    private String keycloakId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
