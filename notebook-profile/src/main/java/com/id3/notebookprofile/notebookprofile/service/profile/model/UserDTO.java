package com.id3.notebookprofile.notebookprofile.service.profile.model;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
