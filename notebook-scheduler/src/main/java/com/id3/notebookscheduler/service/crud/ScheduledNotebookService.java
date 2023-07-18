package com.id3.notebookscheduler.service.crud;

import com.id3.notebookscheduler.service.crud.model.NotebookDTO;
import com.id3.notebookscheduler.service.keycloak.model.UserIdDTO;
import com.id3.notebookscheduler.service.quartz.model.JobInformation;
import com.id3.notebookscheduler.service.quartz.model.TriggerInformation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ScheduledNotebookService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate1;

    public void alert(JobExecutionContext jobExecutionContext) throws ExecutionException, InterruptedException {
        String id = jobExecutionContext.getJobDetail().getKey().getName(); // keycloakId of the notebook
        ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate1.send("notify", id);
        log.warn(jobExecutionContext.getJobDetail().getDescription());
        log.info(String.valueOf(sendResult.get().getRecordMetadata().offset()));
    }
}
