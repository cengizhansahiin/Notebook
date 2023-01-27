package com.id3.notebookscheduler.service.keycloak.model;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
