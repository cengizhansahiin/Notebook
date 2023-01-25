package com.id3.notebookkeycloak.service.keycloak;

import com.id3.notebookkeycloak.constants.KeycloakConstants;
import com.id3.notebookkeycloak.service.keycloak.model.KeycloakRedirectUris;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.RolesRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KeycloakSetupService {
    @Autowired
    KeycloakConstants keycloakConstants;
    
    Keycloak keycloak;

    public void setKeycloak(Keycloak keycloak) {
        this.keycloak = keycloak;
    }
    public void setUp(String realmName, String clientId){

        boolean realmExist = ifRealmExist(realmName);
        if(!realmExist) createRealm(realmName,clientId);
        setClientSecret();
    }
    private void setClientSecret() {
        keycloakConstants.targetClientSecret = keycloak.realm(keycloakConstants.targetRealm)
                .clients()
                .findByClientId(keycloakConstants.targetClientId)
                .get(0)
                .getSecret();
    }
    private void createRealm(String realmName, String clientId) {
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(realmName);
        List<ClientRepresentation> clientRepresentationList = new ArrayList<>();
        clientRepresentationList.add(createClient(clientId));
        realmRepresentation.setClients(clientRepresentationList);
        realmRepresentation.setRoles(createRoles());
        realmRepresentation.setEnabled(true);
        keycloak.realms().create(realmRepresentation);
    }
    private ClientRepresentation createClient(String clientId){
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(clientId);
        clientRepresentation.setRootUrl(keycloakConstants.targetClientRedirectUrl);
        clientRepresentation.setAuthorizationServicesEnabled(true);
        clientRepresentation.setStandardFlowEnabled(true);
        clientRepresentation.setDirectGrantsOnly(true);
        List<String> uris = new ArrayList<>();
        for(KeycloakRedirectUris i: KeycloakRedirectUris.values()) uris.add(i.getURL());
        clientRepresentation.setRedirectUris(uris);
        clientRepresentation.setEnabled(true);
        return clientRepresentation;
    }
    private RolesRepresentation createRoles(){
        List<RoleRepresentation> list = new ArrayList<>();
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName("user");
        list.add(roleRepresentation);
        roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName("admin");
        list.add(roleRepresentation);
        RolesRepresentation rolesRepresentation = new RolesRepresentation();
        rolesRepresentation.setRealm(list);
        return rolesRepresentation;
    }

    private boolean ifRealmExist(String realmName) {
        try{
            RealmRepresentation realmRepresentation = keycloak.realm(realmName).toRepresentation();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
