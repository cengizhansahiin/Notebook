package com.id3.notebookscheduler.external.kafka;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.id3.notebookscheduler.service.keycloak.KeycloakJobService;
import com.id3.notebookscheduler.service.keycloak.model.UserIdDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ProfileSynchronizerService {
    @Autowired
    private KeycloakJobService keycloakJobService;
    @Autowired
    private KafkaTemplate<String, UserIdDTO> kafkaTemplate;

    public void send() throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper(); // or inject it as a dependency
        List<UserIdDTO> users = mapper.convertValue(keycloakJobService.getKeycloakUsers(), new TypeReference<List<UserIdDTO>>() { });
        for(UserIdDTO i: users){
            ListenableFuture<SendResult<String, UserIdDTO>> sendResult = kafkaTemplate.send("sync", i);
            log.info(String.valueOf(sendResult.get().getRecordMetadata().offset()));
        }
    }
}
