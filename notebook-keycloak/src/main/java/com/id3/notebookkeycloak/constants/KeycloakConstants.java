package com.id3.notebookkeycloak.constants;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "keycloak-config")
@Data
public class KeycloakConstants {
    public String realm;
    public String resource;
    public String url;
    public String username;
    public String password;
    public String targetRealm;
    public String targetClientId;
    public String targetClientRedirectUrl;
    public String targetClientSecret;
}
