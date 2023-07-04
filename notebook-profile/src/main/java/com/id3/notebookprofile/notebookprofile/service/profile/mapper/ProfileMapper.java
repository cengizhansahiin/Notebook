package com.id3.notebookprofile.notebookprofile.service.profile.mapper;

import com.id3.notebookprofile.notebookprofile.service.profile.model.Profile;
import com.id3.notebookprofile.notebookprofile.service.profile.model.ProfileDTO;

import java.util.ArrayList;
import java.util.List;

public class ProfileMapper {

    public static Profile toDomain(ProfileDTO profileDTO){
        if(profileDTO != null) {
            Profile profile = new Profile();
            profile.setKeycloakId(profileDTO.getKeycloakId());
            profile.setUsername(profileDTO.getUsername());
            profile.setEmail(profileDTO.getEmail());
            profile.setFirstname(profileDTO.getFirstname());
            profile.setLastname(profileDTO.getLastname());
            profile.setPassword(profileDTO.getPassword());
            return profile;
        }
        else
            return null;
    }

    public static ProfileDTO fromDomain(Profile profile){
        if(profile != null) {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setKeycloakId(profile.getKeycloakId());
            profileDTO.setUsername(profile.getUsername());
            profileDTO.setEmail(profile.getEmail());
            profileDTO.setFirstname(profile.getFirstname());
            profileDTO.setLastname(profile.getLastname());
            profileDTO.setPassword(profile.getPassword());
            return profileDTO;
        }
        else
            return null;
    }

    public static List<ProfileDTO> fromDomain(List<Profile> profileList){
        List<ProfileDTO> profileDTOList = new ArrayList<>();
        for(Profile i : profileList) profileDTOList.add(fromDomain(i));
        return profileDTOList;
    }
}
