package com.id3.notebookkeycloak.service.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.id3.notebookkeycloak.service.keycloak.mapper.KeycloakObjectMapper;
import com.id3.notebookkeycloak.service.keycloak.mapper.TokenMapper;
import com.id3.notebookkeycloak.service.keycloak.model.*;
import com.id3.notebookkeycloak.constants.KeycloakConstants;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KeycloakManagementService {

    @Autowired
    KeycloakConstants keycloakConstants;
    @Autowired
    KeycloakInstanceService keycloakInstanceService;
    @Autowired
    KeycloakSetupService keycloakSetupService;
    Keycloak keycloak;

    @PostConstruct
    public void init(){
        keycloak = keycloakInstanceService.getInstance();
        keycloakSetupService.keycloak = keycloak;
        keycloakSetupService.setUp(keycloakConstants.targetRealm, keycloakConstants.targetClientId);
        log.info("Setup completed!");
    }
    public Response addUser(UserDTO userDTO){
        UserRepresentation user = KeycloakObjectMapper.UserDTOToUserRepresantation(userDTO);
        Keycloak keycloak = keycloakInstanceService.getInstance();
        Response response = keycloak.realm(keycloakConstants.targetRealm).users().create(user);
        addRoleToUser(user);
        return response;
    }

    private void addRoleToUser(UserRepresentation user) {
        RoleRepresentation roleRepresentation = keycloak.realm(keycloakConstants.targetRealm).roles().get(KeycloakRoleEnum.user.name()).toRepresentation();
        keycloak.realm(keycloakConstants.targetRealm).users().get(findUserByUsername(user)).roles().realmLevel().add(List.of(roleRepresentation));
    }
    private String findUserByUsername(UserRepresentation user){
        List<UserRepresentation> list = keycloak.realm(keycloakConstants.targetRealm).users().list();
        for(UserRepresentation userRepresentation: list){
            if(userRepresentation.getUsername().equals(user.getUsername())) return userRepresentation.getId();
        }
        return null;
    }

    public UserDTO getUser(String id) {
        Keycloak keycloak = keycloakInstanceService.getInstance();
        UserResource userResource = keycloak.realm(keycloakConstants.targetRealm).users().get(id);
        UserDTO userDTO = KeycloakObjectMapper.UserResourceToUserDTO(userResource);
        return userDTO;
    }
    public Response deleteUser(String id) {
        Keycloak keycloak = keycloakInstanceService.getInstance();
        Response response = keycloak.realm(keycloakConstants.targetRealm).users().delete(id);
        return response;
    }
    public void updateUser(UserIdDTO userIdDTO){
        Keycloak keycloak = keycloakInstanceService.getInstance();
        UserRepresentation userRepresentation = KeycloakObjectMapper.UserDTOToUserRepresantation(userIdDTO.getUserDTO());
        keycloak.realm(keycloakConstants.targetRealm).users().get(userIdDTO.getId()).update(userRepresentation);
    }
    public TokenDTO login(LoginDTO loginDTO) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = keycloakConstants.url + "/realms/" + keycloakConstants.targetRealm + "/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        MultiValueMap<String, String> entity = new LinkedMultiValueMap<>();
        entity.add("grant_type","password");
        entity.add("client_id",keycloakConstants.targetClientId);
        entity.add("username",loginDTO.getUsername());
        entity.add("password",loginDTO.getPassword());
        entity.add("client_secret",keycloakConstants.getTargetClientSecret());
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(entity,headers);
        String response = restTemplate.postForObject(url,httpEntity, String.class);
        return TokenMapper.StringToTokenDTO(response);
    }
}
