package com.id3.notebookscheduler.service.keycloak;

import com.id3.notebookscheduler.service.keycloak.model.UserIdDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KeycloakJobService {
    @Value("${keycloak-module.api}")
    String apiUrl;

    public List<UserIdDTO> getKeycloakUsers(){
        RestTemplate restTemplate = new RestTemplate();
        List<UserIdDTO> userList = restTemplate.getForObject(apiUrl, ArrayList.class);
        return userList;
    }

}
