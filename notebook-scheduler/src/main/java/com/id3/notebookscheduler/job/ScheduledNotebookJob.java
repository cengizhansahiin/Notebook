package com.id3.notebookscheduler.job;

import com.id3.notebookscheduler.service.crud.ScheduledNotebookService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

public class ScheduledNotebookJob implements Job {

    @Autowired
    private ScheduledNotebookService scheduledNotebookService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            scheduledNotebookService.alert(jobExecutionContext);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
