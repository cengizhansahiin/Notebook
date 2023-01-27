package com.id3.notebookcrud.repository;

import com.id3.notebookcrud.service.crud.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookCrudRepository extends JpaRepository<Notebook, Integer> {
    List<Notebook> findByKeycloakId(String keycloakId);

    @Query(value = "SELECT * FROM notebook WHERE keycloakId = :keycloakId and notebookContent LIKE %:text%", nativeQuery = true)
    List<Notebook> findByText(@Param("text") String text, @Param("keycloakId") String keycloakId);
    @Query(value = "SELECT * FROM notebook WHERE scheduled = true", nativeQuery = true)
    List<Notebook> findByScheduled();
}
