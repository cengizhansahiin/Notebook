package com.id3.notebookkeycloak.service.keycloak.mapper;

import com.id3.notebookkeycloak.service.keycloak.model.UserDTO;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeycloakObjectMapper {

    public static UserDTO UserResourceToUserDTO(UserResource userResource){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userResource.toRepresentation().getEmail());
        userDTO.setFirstname(userResource.toRepresentation().getFirstName());
        userDTO.setLastname(userResource.toRepresentation().getLastName());
        userDTO.setUsername(userResource.toRepresentation().getUsername());
        userDTO.setPassword("");
        return userDTO;
    }
    public static UserRepresentation UserDTOToUserRepresantation(UserDTO userDTO){
        UserRepresentation user = new UserRepresentation();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credentialRepresentation));
        user.setEnabled(true);
        return user;
    }
    public static CredentialRepresentation createPasswordCredentials(String password){
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
