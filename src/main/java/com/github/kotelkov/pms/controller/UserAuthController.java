package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.user.auth.UserAuthCreateDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithRoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithUserProfileDto;
import com.github.kotelkov.pms.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

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
        return userAuthService.getUserAuthById(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public Page<UserAuthDto> getAllUsersAuths(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                              @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                              @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
        return userAuthService.getAllUsersAuths(PageRequest.of(page, size, ASC, sort));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping
    public UserAuthDto updateUserAuth(@Valid @RequestBody UserAuthDto userAuthDto){
        return userAuthService.updateUserAuth(userAuthDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUserAuthById(@PathVariable Long id){
        userAuthService.deleteUserAuthById(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/{id}/profile")
    public UserAuthWithUserProfileDto getUserAuthWithUserProfile(@PathVariable Long id) {
        return userAuthService.getUserAuthWithUserProfile(id);
    }
}
