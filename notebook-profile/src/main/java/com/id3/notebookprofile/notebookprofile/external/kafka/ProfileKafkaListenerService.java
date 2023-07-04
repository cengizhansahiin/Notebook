package com.id3.notebookprofile.notebookprofile.external.kafka;

import com.id3.notebookprofile.notebookprofile.service.profile.ProfileService;
import com.id3.notebookprofile.notebookprofile.service.profile.model.ProfileDTO;
import com.id3.notebookprofile.notebookprofile.service.profile.model.UserDTO;
import com.id3.notebookprofile.notebookprofile.service.profile.model.UserIdDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProfileKafkaListenerService {

    @Autowired
    ProfileService profileService;

    List<ProfileDTO> profileDTOList = profileService.findAllProfiles();
    List<String> keycloakIdList = profileDTOList.stream().map(ProfileDTO::getKeycloakId).collect(Collectors.toList());
    List<String> keycloakIdList1 = new ArrayList<String>();
    int numOfProfiles = profileDTOList.size();

    int count = 0;

    @KafkaListener(topics = "sync", groupId="group-1", containerFactory="userKafkaListenerContainerFactory")
    void listener(UserIdDTO userIdDTO) {
        //keycloakIdList.add(userIdDTO.getId());
        log.info("KeycloakUser [{}]", userIdDTO);
        ProfileDTO profileDTO = profileService.findProfileByKeycloakId(userIdDTO.getId());
        UserDTO userDTO = userIdDTO.getUserDTO();
        if(profileDTO == null) {
            profileDTO = new ProfileDTO(userIdDTO.getId(), userDTO.getUsername(), userDTO.getEmail(),
                    userDTO.getFirstname(), userDTO.getLastname(), userDTO.getPassword());
            profileService.saveProfile(profileDTO);
        }
        else {
            ProfileDTO profileDTO1 = new ProfileDTO(userIdDTO.getId(), userDTO.getUsername(),
                    userDTO.getEmail(), userDTO.getFirstname(), userDTO.getLastname(), userDTO.getPassword());
            if (!(profileDTO.equals(profileDTO1))) {
                profileService.updateProfileByKeycloakId(profileDTO.getKeycloakId(), profileDTO1);
            }
        }
        if(count++ == numOfProfiles) {
            for(String id : keycloakIdList) {
                if(!(keycloakIdList1.contains(id))) {
                    profileService.deleteProfileByKeycloakId(id);
                }
            }
            count = 0;
            profileDTOList = profileService.findAllProfiles();
            numOfProfiles = profileDTOList.size();
        }
    }
}
