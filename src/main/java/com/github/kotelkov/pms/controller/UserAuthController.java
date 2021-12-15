package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.UserAuthDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users_auths")
public class UserAuthController {

    @Autowired
    private Mapper mapper;
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping
    public UserAuthDto createUserAuth(@RequestBody UserAuthDto userAuthDto) {
        return userAuthService.createUserAuth(userAuthDto);
    }

    @GetMapping("/{id}")
    public UserAuthDto getUserAuthById(@PathVariable Long id) {
        UserAuthDto userAuthDto = Optional.ofNullable(userAuthService.getUserAuthById(id)).
                orElseThrow(()->new ResourceNotFoundException("UserAuth with id: "+id+" not found"));
        return userAuthDto;
    }

    @GetMapping
    public List<UserAuthDto> getAllUsersAuths() {
        List<UserAuthDto> userAuthDtoList = Optional.ofNullable(userAuthService.getAllUsersAuths()).
                orElseThrow(()->new ResourceNotFoundException("UsersAuths not found"));
        return userAuthDtoList;
    }

    @PutMapping
    public UserAuthDto updateUserAuth(@RequestBody UserAuthDto userAuthDto){
        return userAuthService.updateUserAuth(userAuthDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserAuth(@PathVariable Long id){
        userAuthService.deleteUserAuth(id);
        return ResponseEntity.noContent().build();
    }
}
