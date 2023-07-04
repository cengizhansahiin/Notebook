package com.id3.notebookprofile.notebookprofile.repository;

import com.id3.notebookprofile.notebookprofile.service.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{
    Profile findByKeycloakId(String keycloakId);
}

