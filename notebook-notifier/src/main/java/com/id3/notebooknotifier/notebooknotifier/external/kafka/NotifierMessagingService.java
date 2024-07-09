package com.id3.notebooknotifier.notebooknotifier.external.kafka;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotifierMessagingService {

    @Autowired
    private final FirebaseMessaging fcm;

    List<String> keyCloakIdList;

    public NotifierMessagingService(FirebaseMessaging fcm) {
        this.fcm = fcm;
        this.keyCloakIdList = new ArrayList<String>();
    }

    @KafkaListener(topics = "notify", groupId="group-1", containerFactory="userKafkaListenerContainerFactory")
    void listener(String id){
        log.info("KeycloakUser [{}]", id);
        keyCloakIdList.add(id);
        // id ve token ile mesaj oluştur
        // oluşan mesajı yolla
    }

}