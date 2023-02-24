package com.id3.notebookprofile.notebookprofile.repository;

import com.id3.notebookprofile.notebookprofile.service.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{
    List<Profile> findByKeycloakId(String keycloakId);
}

