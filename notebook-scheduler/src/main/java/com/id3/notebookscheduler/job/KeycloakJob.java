package com.id3.notebookscheduler.job;

import com.id3.notebookscheduler.service.keycloak.KeycloakJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KeycloakJob implements Job {
    @Autowired
    KeycloakJobService keycloakJobService;

    public void execute(JobExecutionContext context){
        keycloakJobService.getKeycloakUsers();
    }
}
