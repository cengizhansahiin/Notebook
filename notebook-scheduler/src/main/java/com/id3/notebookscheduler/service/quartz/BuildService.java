package com.id3.notebookscheduler.service.quartz;

import com.id3.notebookscheduler.constants.CrudJobConstants;
import com.id3.notebookscheduler.job.CrudJob;
import com.id3.notebookscheduler.job.ScheduledNotebookJob;
import com.id3.notebookscheduler.service.quartz.model.JobInformation;
import com.id3.notebookscheduler.service.quartz.model.TriggerInformation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Service
@Slf4j
public class BuildService {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private JobDetail createJob(JobInformation jobInformation){
        return JobBuilder.newJob().ofType(ScheduledNotebookJob.class)
                .storeDurably()
                .withIdentity(jobInformation.getId(),"group 3")
                .withDescription("Scheduled notebook job")
                .build();
    }
    private CronTrigger createTrigger(TriggerInformation triggerInformation, JobDetail job){
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity(triggerInformation.getId(),"group 3")
                .withSchedule(cronSchedule(triggerInformation.getCron()))
                .withDescription("Scheduled notebook trigger")
                .build();
    }
    public void scheduleJob(JobInformation jobInformation, TriggerInformation triggerInformation) throws SchedulerException {
        JobDetail job = createJob(jobInformation);
        CronTrigger trigger = createTrigger(triggerInformation,job);
        if(!doesExist(jobInformation.getId())){
            schedulerFactoryBean.getObject().scheduleJob(job,trigger);
        }
        else{
            log.error("Job already exists!!!");
        }
    }
    public boolean doesExist(String id) throws SchedulerException {
        for(JobKey key: schedulerFactoryBean.getObject().getJobKeys(GroupMatcher.jobGroupEquals("group 3"))){
            if(key.getName().equals(id)) return true;
        }
        return false;
    }




}
