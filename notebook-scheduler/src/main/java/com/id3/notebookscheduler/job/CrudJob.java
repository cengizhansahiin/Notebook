package com.id3.notebookscheduler.job;


import com.id3.notebookscheduler.service.crud.CrudJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class CrudJob implements Job {

    @Autowired
    CrudJobService crudJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        crudJobService.getScheduledNotebooks();
    }
}
