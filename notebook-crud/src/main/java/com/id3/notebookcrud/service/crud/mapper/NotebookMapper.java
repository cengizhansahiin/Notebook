package com.id3.notebookcrud.service.crud.mapper;

import com.id3.notebookcrud.service.crud.model.Notebook;
import com.id3.notebookcrud.service.crud.model.NotebookDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotebookMapper {
    public static Notebook DTOtoEntity(NotebookDTO notebookDTO){
        Notebook notebook = new Notebook();
        notebook.setKeycloakId(notebookDTO.getKeycloakId());
        notebook.setNotebookContent(notebookDTO.getNotebookContent());
        notebook.setScheduled(notebookDTO.isScheduled());
        notebook.setScheduleCron(notebookDTO.getScheduleCron());
        notebook.setActive(true);
        notebook.setCreatedOn(LocalDateTime.now());
        return notebook;
    }
    public static NotebookDTO EntityToDTO(Notebook notebook){
        NotebookDTO notebookDTO = new NotebookDTO();
        notebookDTO.setKeycloakId(notebook.getKeycloakId());
        notebookDTO.setNotebookContent(notebook.getNotebookContent());
        notebookDTO.setScheduled(notebook.isScheduled());
        notebookDTO.setScheduleCron(notebook.getScheduleCron());
        return notebookDTO;

    }
    public static List<NotebookDTO> EntityListToDTOList(List<Notebook> notebooks) {
        List<NotebookDTO> list = new ArrayList<NotebookDTO>();
        for(Notebook i: notebooks) list.add(EntityToDTO(i));
        return list;
    }

}
