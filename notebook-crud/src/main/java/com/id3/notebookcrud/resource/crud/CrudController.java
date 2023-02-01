package com.id3.notebookcrud.resource.crud;

import com.id3.notebookcrud.service.crud.model.NotebookDTO;
import com.id3.notebookcrud.service.crud.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/crud")
public class CrudController {
    @Autowired
    CrudService crudService;

    @RolesAllowed("user")
    @GetMapping("/{id}/notebooks")
    public List<NotebookDTO> getAllNotebooks(@PathVariable("id") String id){
        List<NotebookDTO> list = crudService.getAllNotebooks(id);
        log.info("Response for API api/crud/"+ id + "/notebooks: " + list.toString());
        return list;
    }
    @RolesAllowed("user")
    @PostMapping("/create")
    public void createNotebook(@RequestBody NotebookDTO notebookDTO){
        log.info("Notebook created from API api/crud/create: " + notebookDTO.toString());
        crudService.createNotebook(notebookDTO);
    }
    @RolesAllowed("user")
    @DeleteMapping("/delete/{keycloakId}/{notebookId}")
    public void deleteNotebook(@PathVariable int notebookId,@PathVariable String keycloakId){
        crudService.deleteNotebook(notebookId,keycloakId);
    }
    @RolesAllowed("user")
    @GetMapping("/notebook/find/id/{keycloakId}/{notebookId}")
    public NotebookDTO getNotebook(@PathVariable int notebookId,@PathVariable String keycloakId){
        NotebookDTO notebookDTO = crudService.getNotebook(notebookId,keycloakId);
        return notebookDTO;
    }
    @RolesAllowed("user")
    @GetMapping("/notebook/find/text/{keycloakId}/{text}")
    public List<NotebookDTO> getNotebooksByText(@PathVariable String keycloakId, @PathVariable String text){
        List<NotebookDTO> list = crudService.getNotebooksByText(text,keycloakId);
        log.info("Response for API api/crud/notebook/find/text/"+ keycloakId + "/" + text + ": " + list.toString());
        return list;
    }
    @RolesAllowed("user")
    @PutMapping("/update/{id}")
    public void updateNotebook(@RequestBody NotebookDTO notebookDTO, @PathVariable int id){
        crudService.updateNotebook(notebookDTO,id);
    }
    @GetMapping("/scheduled")
    public List<NotebookDTO> getScheduledNotebooks(){
        List<NotebookDTO> notebooks = crudService.getScheduledNotebooks();
        return notebooks;
    }
}
