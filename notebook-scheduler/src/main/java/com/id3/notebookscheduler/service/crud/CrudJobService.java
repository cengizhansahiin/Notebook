package com.id3.notebookscheduler.service.crud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CrudJobService {

    @Value("${crud-module.api}")
    String apiUrl;

    public void getScheduledNotebooks(){
        log.info("Crud job is executed.");
    }
}
