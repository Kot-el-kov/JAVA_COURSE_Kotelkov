package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.UserProfileDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users_profiles")
public class UserProfileController {


    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity createUserProfile(@RequestBody UserProfileDto userProfileDto) {
        userProfileService.createUserProfile(userProfileDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserProfileById(@PathVariable Long id) {
        UserProfileDto userProfileDto = Optional.ofNullable(userProfileService.getUserProfileById(id)).
                orElseThrow(()->new ResourceNotFoundException("UserProfile with id: "+id+" not found"));
        return ResponseEntity.ok(userProfileDto);
    }

    @RequestMapping(value = "/all")
    public ResponseEntity getAllUsersProfiles() {
        List<UserProfileDto> userProfileDtoList = Optional.ofNullable(userProfileService.getAllUsersProfiles()).
                orElseThrow(()->new ResourceNotFoundException("UsersProfiles not found"));
        return ResponseEntity.ok(userProfileDtoList);
    }

    @PutMapping
    public ResponseEntity updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(userProfileDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserProfileById(@PathVariable Long id) {
        userProfileService.deleteUserProfileById(id);
        return ResponseEntity.noContent().build();
    }
}
