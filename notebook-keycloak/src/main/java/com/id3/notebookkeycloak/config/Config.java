package com.id3.notebookkeycloak.config;

import com.id3.notebookkeycloak.constants.KeycloakConstants;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeycloakConstants.class)
public class Config {
}
