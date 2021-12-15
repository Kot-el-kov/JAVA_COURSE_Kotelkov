package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.UserAuthDto;

import java.util.List;

public interface UserAuthService {
    UserAuthDto createUserAuth(UserAuthDto userAuthDto);
    UserAuthDto getUserAuthById(Long id);
    List<UserAuthDto> getAllUsersAuths();
    UserAuthDto updateUserAuth(UserAuthDto userAuthDto);
    void deleteUserAuth(Long id);
}
