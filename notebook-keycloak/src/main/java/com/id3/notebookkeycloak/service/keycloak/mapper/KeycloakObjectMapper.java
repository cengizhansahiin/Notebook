package com.id3.notebookkeycloak.service.keycloak.mapper;

import com.id3.notebookkeycloak.service.keycloak.model.UserDTO;
import com.id3.notebookkeycloak.service.keycloak.model.UserIdDTO;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeycloakObjectMapper {

    public static UserDTO UserRepresentationToUserDTO(UserRepresentation userRepresentation){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userRepresentation.getEmail());
        userDTO.setFirstname(userRepresentation.getFirstName());
        userDTO.setLastname(userRepresentation.getLastName());
        userDTO.setUsername(userRepresentation.getUsername());
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
    public static List<UserIdDTO> UserRepresentationListToUserIdDTOList(List<UserRepresentation> users){
        List<UserIdDTO> usersDTO = new ArrayList<>();
        for(UserRepresentation u: users){
            UserDTO userDTO = UserRepresentationToUserDTO(u);
            UserIdDTO userIdDTO = new UserIdDTO();
            userIdDTO.setUserDTO(userDTO);
            userIdDTO.setId(u.getId());
            usersDTO.add(userIdDTO);
        }
        return usersDTO;
    }
}
