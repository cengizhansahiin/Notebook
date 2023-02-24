package com.id3.notebookscheduler.job;

import com.id3.notebookscheduler.external.kafka.ProfileSynchronizerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KeycloakJob implements Job {
    @Autowired
    ProfileSynchronizerService profileSynchronizerService;

    public void execute(JobExecutionContext context){
        try {
            profileSynchronizerService.send();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
