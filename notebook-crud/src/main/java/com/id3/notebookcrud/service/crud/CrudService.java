package com.id3.notebookcrud.service.crud;

import com.id3.notebookcrud.service.crud.mapper.NotebookMapper;
import com.id3.notebookcrud.service.crud.model.Notebook;
import com.id3.notebookcrud.service.crud.model.NotebookDTO;
import com.id3.notebookcrud.repository.NotebookCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Slf4j
public class CrudService {

    @Autowired
    NotebookCrudRepository notebookCrudRepository;
    public List<NotebookDTO> getAllNotebooks(String id) {
        List<NotebookDTO> list = NotebookMapper.EntityListToDTOList(notebookCrudRepository.findByKeycloakId(id));
        return list;
    }
    public void createNotebook(NotebookDTO notebookDTO) {
        Notebook notebook = NotebookMapper.DTOtoEntity(notebookDTO);
        notebookCrudRepository.save(notebook);
    }
    public ResponseEntity<String> deleteNotebook(int notebookId, String keycloakId){
        if(notebookCrudRepository.findById(notebookId).get().getKeycloakId().equals(keycloakId)){
            notebookCrudRepository.deleteById(notebookId);
            return new ResponseEntity<>("Successfully deleted.",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete. User does not own the notebook", HttpStatus.FORBIDDEN);
    }
    public NotebookDTO getNotebook(int notebookId, String keycloakId){
        if(notebookCrudRepository.findById(notebookId).get().getKeycloakId().equals(keycloakId)){
            return NotebookMapper.EntityToDTO(notebookCrudRepository.findById(notebookId).get());
        }
        return null;
    }
    public List<NotebookDTO> getNotebooksByText(String text, String keycloakId){
        List<Notebook> list = notebookCrudRepository.findByText(text,keycloakId);
        return NotebookMapper.EntityListToDTOList(list);
    }

    public void updateNotebook(NotebookDTO notebookDTO, int id) {
        Notebook notebook = NotebookMapper.DTOtoEntity(notebookDTO);
        notebook.setNotebookId(id);
        notebookCrudRepository.save(notebook);
    }
}
