package com.id3.notebookkeycloak.service.keycloak.model;

public enum KeycloakRedirectUris {
    keycloak{
        public String getURL(){
            return "http://localhost:8180";
        }
    },
    crud{
        public String getURL(){
            return "http://localhost:8280";
        }
    };
    public abstract String getURL();
}
