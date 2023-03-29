package com.id3.notebookscheduler.job;

import com.id3.notebookscheduler.service.crud.ScheduledNotebookService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduledNotebookJob implements Job {

    @Autowired
    private ScheduledNotebookService scheduledNotebookService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scheduledNotebookService.alert();
    }
}
