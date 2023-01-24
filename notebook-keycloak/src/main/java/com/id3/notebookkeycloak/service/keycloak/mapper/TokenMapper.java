package com.id3.notebookkeycloak.service.keycloak.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.id3.notebookkeycloak.service.keycloak.model.TokenDTO;

public class TokenMapper {
    public static TokenDTO StringToTokenDTO(String accessToken) throws JsonProcessingException {
        TokenDTO tokenDTO = new TokenDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode token = objectMapper.readTree(accessToken);
        String tokenValue = token.get("access_token").textValue();
        tokenDTO.setAccessToken(tokenValue);
        return tokenDTO;
    }
}
