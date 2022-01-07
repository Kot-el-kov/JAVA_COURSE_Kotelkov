package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.user.auth.UserAuthCreateDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithRoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithUserProfileDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users_auths")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping
    public UserAuthWithRoleDto createUserAuth(@Valid @RequestBody UserAuthCreateDto userAuthCreateDto) {
        return userAuthService.createUserAuth(userAuthCreateDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserAuthDto getUserAuthById(@PathVariable Long id) {
        UserAuthDto userAuthDto = Optional.ofNullable(userAuthService.getUserAuthById(id)).
                orElseThrow(()->new ResourceNotFoundException("UserAuth with id: "+id+" not found"));
        return userAuthDto;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public Page<UserAuthDto> getAllUsersAuths(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                              @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                              @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
        Page<UserAuthDto> userAuthDtoList = userAuthService.getAllUsersAuths(PageRequest.of(page, size, ASC, sort));
        return userAuthDtoList;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping
    public UserAuthDto updateUserAuth(@Valid @RequestBody UserAuthDto userAuthDto){
        return userAuthService.updateUserAuth(userAuthDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserAuthById(@PathVariable Long id){
        userAuthService.deleteUserAuthById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/profile/{id}")
    public UserAuthWithUserProfileDto getUserAuthWithUserProfile(@PathVariable Long id) {
        UserAuthWithUserProfileDto userAuthWithUserProfileDto = userAuthService.getUserAuthWithUserProfile(id);
        Optional.ofNullable(userAuthWithUserProfileDto.getUserProfileDto()).
                orElseThrow(()->new ResourceNotFoundException("This user does not have profile"));
        return userAuthWithUserProfileDto;
    }
}
