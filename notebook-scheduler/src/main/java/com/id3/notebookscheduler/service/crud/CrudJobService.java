package com.id3.notebookscheduler.service.crud;


import com.id3.notebookscheduler.service.crud.mapper.CrudMapper;
import com.id3.notebookscheduler.service.crud.model.NotebookDTO;
import com.id3.notebookscheduler.service.quartz.BuildService;
import com.id3.notebookscheduler.service.quartz.model.JobInformation;
import com.id3.notebookscheduler.service.quartz.model.TriggerInformation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CrudJobService {

    @Value("${crud-module.api}")
    String apiUrl;
    @Autowired
    BuildService buildService;

    private List<NotebookDTO> getScheduledNotebooks(){
        RestTemplate restTemplate = new RestTemplate();
        List<NotebookDTO> list = restTemplate.getForObject(apiUrl, ArrayList.class);
        return list;
    }
    public void createJobsForScheduledNotebooks() throws SchedulerException {
        List<NotebookDTO> list = getScheduledNotebooks();
        List<Map<JobInformation,TriggerInformation>> jobs = CrudMapper.NotebookDTOtoQuartzInformation(list);
        for(Map<JobInformation, TriggerInformation> i: jobs){
            JobInformation job = (JobInformation) i.keySet().toArray()[0];
            // i can change the scheduleJob method to not check keycloakid's existence
            TriggerInformation trigger = (TriggerInformation) i.values().toArray()[0];
            buildService.scheduleJob(job,trigger);
        }
    }
}
