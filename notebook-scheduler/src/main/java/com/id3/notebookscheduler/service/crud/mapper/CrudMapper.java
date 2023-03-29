package com.id3.notebookscheduler.service.crud.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.id3.notebookscheduler.service.crud.model.NotebookDTO;
import com.id3.notebookscheduler.service.quartz.model.JobInformation;
import com.id3.notebookscheduler.service.quartz.model.TriggerInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudMapper {
    public static List<Map<JobInformation, TriggerInformation>> NotebookDTOtoQuartzInformation(List<NotebookDTO> list){
        List<Map<JobInformation, TriggerInformation>> quartzList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(int i = 0;i<list.size();i++){
            NotebookDTO notebookDTO = mapper.convertValue(list.get(i),NotebookDTO.class);
            JobInformation jobInformation = NotebookDTOtoJobInformation(notebookDTO);
            TriggerInformation triggerInformation = NotebookDTOtoTriggerInformation(notebookDTO);
            Map<JobInformation,TriggerInformation> informationMap = new HashMap<>();
            informationMap.put(jobInformation,triggerInformation);
            quartzList.add(informationMap);
        }
        return quartzList;
    }
    public static JobInformation NotebookDTOtoJobInformation(NotebookDTO notebookDTO){
        JobInformation jobInformation = new JobInformation();
        jobInformation.setId(notebookDTO.getKeycloakId());
        return jobInformation;
    }
    public static TriggerInformation NotebookDTOtoTriggerInformation(NotebookDTO notebookDTO){
        TriggerInformation triggerInformation = new TriggerInformation();
        triggerInformation.setId(notebookDTO.getKeycloakId());
        triggerInformation.setCron(notebookDTO.getScheduleCron());
        return triggerInformation;
    }
}
