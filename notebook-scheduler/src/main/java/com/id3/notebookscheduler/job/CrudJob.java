package com.id3.notebookscheduler.job;


import com.id3.notebookscheduler.service.crud.CrudJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrudJob implements Job {
    @Autowired
    CrudJobService crudJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            crudJobService.createJobsForScheduledNotebooks();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
