package com.id3.notebookscheduler.service.crud;


import com.id3.notebookscheduler.service.crud.model.NotebookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CrudJobService {

    @Value("${crud-module.api}")
    String apiUrl;

    public List<NotebookDTO> getScheduledNotebooks(){
        RestTemplate restTemplate = new RestTemplate();
        List<NotebookDTO> list = restTemplate.getForObject(apiUrl, ArrayList.class);
        return list;
    }
    public void createJobsForNotebooks(){};
}
