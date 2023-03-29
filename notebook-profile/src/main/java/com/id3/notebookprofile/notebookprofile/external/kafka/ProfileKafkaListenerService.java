package com.id3.notebookprofile.notebookprofile.external.kafka;

import com.id3.notebookprofile.notebookprofile.service.profile.model.UserIdDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfileKafkaListenerService {
    @KafkaListener(topics = "sync", groupId="group-1", containerFactory="userKafkaListenerContainerFactory")
    void listener(UserIdDTO userIdDTO) {
        log.info("KeycloakUser [{}]", userIdDTO);
    }
}
