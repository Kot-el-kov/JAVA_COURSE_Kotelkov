package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.UserAuthDto;

import java.util.List;

public interface UserAuthService {
    void createUserAuth(UserAuthDto userAuthDto);
    UserAuthDto getUserAuthById(Long id);
    List<UserAuthDto> getAllUsersAuths();
    void updateUserAuth(UserAuthDto userAuthDto);
    void deleteUserAuth(Long id);
}
