package com.id3.notebookscheduler.config;

import com.id3.notebookscheduler.constants.CrudJobConstants;
import com.id3.notebookscheduler.constants.KeycloakJobConstants;
import com.id3.notebookscheduler.job.CrudJob;
import com.id3.notebookscheduler.job.KeycloakJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Configuration
@Slf4j
public class QuartzConfig {

    @Autowired
    ApplicationContext applicationContext;
    private final String keycloakJob = "keycloakJob";
    private final String keycloakTrigger = "keycloakTrigger";
    private final String crudJob = "crudJob";
    private final String crudTrigger = "crudTrigger";

    //Helps us to create jobs with our keycloak classes
    @Bean(name = keycloakJob)
    public JobDetailFactoryBean keycloakJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(KeycloakJob.class);
        jobDetailFactory.setGroup(KeycloakJobConstants.groupName);
        jobDetailFactory.setName(KeycloakJobConstants.jobIdentity);
        jobDetailFactory.setDescription(KeycloakJobConstants.jobDescription);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    //Helps us to create cron trigger
    @Bean(name = keycloakTrigger)
    public CronTriggerFactoryBean keycloakTrigger(@Qualifier("keycloakJob") JobDetail job){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setStartTime(new Date());
        trigger.setCronExpression(KeycloakJobConstants.cronExpression);
        trigger.setName(KeycloakJobConstants.triggerIdentity);
        trigger.setDescription(KeycloakJobConstants.triggerDescription);
        trigger.setGroup(KeycloakJobConstants.groupName);
        return trigger;
    }
    @Bean(name = crudJob)
    public JobDetailFactoryBean crudJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(CrudJob.class);
        jobDetailFactory.setGroup(CrudJobConstants.groupName);
        jobDetailFactory.setName(CrudJobConstants.jobIdentity);
        jobDetailFactory.setDescription(CrudJobConstants.jobDescription);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    @Bean(name = crudTrigger)
    public CronTriggerFactoryBean crudTrigger(@Qualifier("crudJob") JobDetail job){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setStartTime(new Date());
        trigger.setCronExpression(CrudJobConstants.cronExpression);
        trigger.setName(CrudJobConstants.triggerIdentity);
        trigger.setDescription(CrudJobConstants.triggerDescription);
        trigger.setGroup(CrudJobConstants.groupName);
        return trigger;
    }


    //This bean is used for to introduce configured quartz beans to quartz
    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    //This bean combines jobs and triggers
    @Bean
    public SchedulerFactoryBean scheduler(Map<String, JobDetail> jobMap, Map<String, Trigger> triggerMap) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobFactory(springBeanJobFactory());
        JobDetail[] jobs = jobMap.values().toArray(new JobDetail[0]);
        Trigger[] tr = triggerMap.values().toArray(new Trigger[0]);
        schedulerFactory.setJobDetails(jobs);
        schedulerFactory.setTriggers(tr);
        return schedulerFactory;
    }


}
