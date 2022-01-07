package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.user.auth.UserAuthCreateDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithRoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithUserProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAuthService {
    UserAuthWithRoleDto createUserAuth(UserAuthCreateDto userAuthCreateDto);
    UserAuthDto getUserAuthById(Long id);
    Page<UserAuthDto> getAllUsersAuths(Pageable pageable);
    UserAuthDto updateUserAuth(UserAuthDto userAuthDto);
    void deleteUserAuthById(Long id);
    UserAuthDto getUserAuthByLogin(String login);
    UserAuthWithUserProfileDto getUserAuthWithUserProfile(Long id);
}
