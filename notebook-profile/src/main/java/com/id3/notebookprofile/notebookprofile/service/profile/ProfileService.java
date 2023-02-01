package com.id3.notebookprofile.notebookprofile.service.profile;

import com.id3.notebookprofile.notebookprofile.service.profile.model.Profile;
import com.id3.notebookprofile.notebookprofile.service.profile.model.ProfileDTO;
import com.id3.notebookprofile.notebookprofile.service.profile.mapper.ProfileMapper;
import com.id3.notebookprofile.notebookprofile.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProfileService {

    @Autowired
    ProfileRepository profileCrudRepository;

    public List<ProfileDTO> findAllProfiles(){
        return ProfileMapper.fromDomain(profileCrudRepository.findAll());
    }

    public void saveProfile(ProfileDTO profileDTO){
        Profile profile = ProfileMapper.toDomain(profileDTO);
        profileCrudRepository.save(profile);
    }

    public ResponseEntity<String> deleteProfile(int profileId){
        if(profileCrudRepository.existsById(profileId)){
            profileCrudRepository.deleteById(profileId);
            return new ResponseEntity<>("Profile deleted.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Profile not found.", HttpStatus.NOT_FOUND);
        }
    }

    public ProfileDTO findProfile(int profileId){
        if(profileCrudRepository.existsById(profileId)){
            return ProfileMapper.fromDomain(profileCrudRepository.findById(profileId).get());
        }
        return null;
    }

    public void updateProfile(int id, ProfileDTO profileDTO){
        Profile profile = profileCrudRepository.findById(id).get();
        profile.setKeycloakId(profileDTO.getKeycloakId());
        profile.setUsername(profileDTO.getUsername());
        profile.setEmail(profileDTO.getEmail());
        profile.setFirstname(profileDTO.getFirstname());
        profile.setLastname(profileDTO.getLastname());
        profile.setPassword(profileDTO.getPassword());
        profileCrudRepository.save(profile);
    }
}
