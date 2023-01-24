package com.id3.notebookkeycloak.service.keycloak;

import com.id3.notebookkeycloak.constants.KeycloakConstants;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KeycloakInstanceService {
    @Autowired
    KeycloakConstants keycloakConstants;
    public Keycloak getInstance(){
        return KeycloakBuilder.builder()
                .serverUrl(keycloakConstants.url)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakConstants.realm)
                .clientId(keycloakConstants.resource)
                .username(keycloakConstants.username)
                .password(keycloakConstants.password)
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                )
                .build();
    }

}
