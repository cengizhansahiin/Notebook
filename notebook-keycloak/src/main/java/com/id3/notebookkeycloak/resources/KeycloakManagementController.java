package com.id3.notebookkeycloak.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.id3.notebookkeycloak.service.keycloak.KeycloakManagementService;
import com.id3.notebookkeycloak.service.keycloak.model.LoginDTO;
import com.id3.notebookkeycloak.service.keycloak.model.TokenDTO;
import com.id3.notebookkeycloak.service.keycloak.model.UserIdDTO;
import com.id3.notebookkeycloak.service.keycloak.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@Slf4j
@RequestMapping(path="api/user")
public class KeycloakManagementController {
    @Autowired
    KeycloakManagementService keycloakManagementService;

    @PostMapping("/create")
    public void addUser(@RequestBody UserDTO userDTO){
        Response response = keycloakManagementService.addUser(userDTO);
        response.getStatus();
        log.info("Response for API api/user/create: " + response.getStatus());
    }
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") String id){
        UserDTO user = keycloakManagementService.getUser(id);
        log.info("Response for API api/user/" + id + ": " + user.toString());
        return user;
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") String id){
        Response response = keycloakManagementService.deleteUser(id);
        log.info("Response for API api/user/delete" + id + ": " + response.getStatus());
    }
    @PutMapping("/update")
    public void updateUser(@RequestBody UserIdDTO userIdDTO){
        keycloakManagementService.updateUser(userIdDTO);
        log.info("Response for API api/user/update: "+ keycloakManagementService.getUser(userIdDTO.getId()).toString());
    }
    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO) throws JsonProcessingException {
        return keycloakManagementService.login(loginDTO);
    }
}
