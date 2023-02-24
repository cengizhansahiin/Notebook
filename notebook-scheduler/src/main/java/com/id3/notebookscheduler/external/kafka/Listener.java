package com.id3.notebookscheduler.external.kafka;

import com.id3.notebookscheduler.service.keycloak.model.UserIdDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener {
    @KafkaListener(topics = "sync",groupId = "sync", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(UserIdDTO user){
        log.info(user.toString());
    }
}
