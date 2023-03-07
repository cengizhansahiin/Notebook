package com.id3.notebookscheduler.service.crud;

import com.id3.notebookscheduler.service.crud.model.NotebookDTO;
import com.id3.notebookscheduler.service.quartz.model.JobInformation;
import com.id3.notebookscheduler.service.quartz.model.TriggerInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ScheduledNotebookService {
    public void alert(){

        log.warn("Alert");
    }
}
