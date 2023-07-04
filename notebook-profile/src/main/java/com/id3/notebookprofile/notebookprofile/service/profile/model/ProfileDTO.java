package com.id3.notebookprofile.notebookprofile.service.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private String keycloakId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
