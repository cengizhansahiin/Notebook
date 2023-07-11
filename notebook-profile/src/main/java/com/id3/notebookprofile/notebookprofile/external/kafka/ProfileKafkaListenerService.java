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

    ProfileService profileService;
    List<ProfileDTO> profileDTOList;
    List<String> keycloakIdList;
    List<String> keycloakIdList1;
    int numOfProfiles;
    int count;

    @Autowired
    public ProfileKafkaListenerService(ProfileService profileService) {
        this.profileService = profileService;
        this.profileDTOList = profileService.findAllProfiles();
        this.keycloakIdList = profileDTOList.stream().map(ProfileDTO::getKeycloakId).collect(Collectors.toList());
        this.keycloakIdList1 = new ArrayList<String>();
        this.numOfProfiles = profileDTOList.size();
        this.count = 0;
    }

    @KafkaListener(topics = "sync", groupId="group-1", containerFactory="userKafkaListenerContainerFactory")
    void listener(UserIdDTO userIdDTO) {
        log.info("KeycloakUser [{}]", userIdDTO);
        String id = userIdDTO.getId();
        if(!(keycloakIdList1.contains(id))) {
            keycloakIdList1.add(id);
        }
        ProfileDTO profileDTO = profileService.findProfileByKeycloakId(userIdDTO.getId());
        UserDTO userDTO = userIdDTO.getUserDTO();
        if(profileDTO == null) {
            profileDTO = new ProfileDTO(userIdDTO.getId(), userDTO.getUsername(), userDTO.getEmail(),
                    userDTO.getFirstname(), userDTO.getLastname(), userDTO.getPassword());
            profileService.saveProfile(profileDTO);
            numOfProfiles++;
        }
        else {
            ProfileDTO profileDTO1 = new ProfileDTO(userIdDTO.getId(), userDTO.getUsername(),
                    userDTO.getEmail(), userDTO.getFirstname(), userDTO.getLastname(), userDTO.getPassword());
            if (!(profileDTO.equals(profileDTO1))) {
                profileService.updateProfileByKeycloakId(profileDTO.getKeycloakId(), profileDTO1);
            }
        }
        count++;
        if(count == numOfProfiles) {
            for(String s : keycloakIdList) {
                if(!(keycloakIdList1.contains(s))) {
                    profileService.deleteProfileByKeycloakId(s);
                }
            }
            profileDTOList = profileService.findAllProfiles();
            keycloakIdList = profileDTOList.stream().map(ProfileDTO::getKeycloakId).collect(Collectors.toList());
            keycloakIdList1.clear();
            numOfProfiles = profileDTOList.size();
            count = 0;
        }
    }
}
