package com.id3.notebookprofile.notebookprofile.resource.profile;

import com.id3.notebookprofile.notebookprofile.service.profile.ProfileService;
import com.id3.notebookprofile.notebookprofile.service.profile.model.ProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @RolesAllowed("user")
    @GetMapping("/profiles")
    public List<ProfileDTO> findAllProfiles(){
        List<ProfileDTO> list = profileService.findAllProfiles();
        log.info("Response for API api/profiles: " + list.toString());
        return list;
    }

    @RolesAllowed("user")
    @PostMapping("/create")
    public void saveProfile(@RequestBody ProfileDTO profileDTO){
        log.info("Profile created from API api/profile/create: " + profileDTO.toString());
        profileService.saveProfile(profileDTO);
    }

    @RolesAllowed("user")
    @DeleteMapping("/delete/{profileId}")
    public void deleteProfile(@PathVariable int profileId){
        profileService.deleteProfile(profileId);
    }

    @RolesAllowed("user")
    @GetMapping("/profile/find/{profileId}")
    public ProfileDTO findProfile(@PathVariable int profileId){
        return profileService.findProfile(profileId);
    }

    @RolesAllowed("user")
    @PutMapping("/update/{id}")
    public void updateProfile(@PathVariable int id, @RequestBody ProfileDTO profileDTO){
        profileService.updateProfile(id, profileDTO);
    }
}
