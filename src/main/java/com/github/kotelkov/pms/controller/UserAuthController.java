package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.UserAuthDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity createUserAuth(@RequestBody UserAuthDto userAuthDto) {
        userAuthService.createUserAuth(userAuthDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserAuthById(@PathVariable Long id) {
        UserAuthDto userAuthDto = Optional.ofNullable(userAuthService.getUserAuthById(id)).
                orElseThrow(()->new ResourceNotFoundException("UserAuth with id: "+id+" not found"));
        return ResponseEntity.ok(userAuthDto);
    }

    @RequestMapping(value = "/all")
    public ResponseEntity getAllUsersAuths() {
        List<UserAuthDto> userAuthDtoList = Optional.ofNullable(userAuthService.getAllUsersAuths()).
                orElseThrow(()->new ResourceNotFoundException("UsersAuths not found"));
        return ResponseEntity.ok(userAuthDtoList);
    }

    @PutMapping
    public ResponseEntity updateUserAuth(@RequestBody UserAuthDto userAuthDto){
        return ResponseEntity.ok(userAuthService.updateUserAuth(userAuthDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserAuth(@PathVariable Long id){
        userAuthService.deleteUserAuth(id);
        return ResponseEntity.noContent().build();
    }

}
