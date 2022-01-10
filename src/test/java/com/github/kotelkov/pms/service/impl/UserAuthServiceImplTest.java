package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.dao.impl.RoleRepositoryImpl;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthCreateDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithRoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithUserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.entity.*;
import com.github.kotelkov.pms.mapper.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

class UserAuthServiceImplTest {
    @Mock
    UserAuthRepository userAuthRepository;
    @Mock
    Mapper mapper;
    @Mock
    BCryptPasswordEncoder passwordEncoder;
    @Mock
    RoleRepositoryImpl roleRepository;
    @InjectMocks
    UserAuthServiceImpl userAuthServiceImpl;

    private final UserAuth userAuth = UserAuth.builder().id(1L).
            role(Role.builder().id(1L).name("role").build()).login("login").password("password").
            userProfile(UserProfile.builder().id(1L).name("name").build()).build();
    private final UserAuthCreateDto userAuthCreateDto = UserAuthCreateDto.builder().role("role").
            login("login").password("password").build();
    private final UserAuthDto userAuthDto = UserAuthDto.builder().id(1L).login("login").password("password").build();
    private final UserAuthWithRoleDto userAuthWithRoleDto = UserAuthWithRoleDto.builder().
            roleDto(RoleDto.builder().id(1L).name("name").build()).login("login").password("password").build();
    private final Role role = Role.builder().id(1L).name("name").build();
    private final RoleDto roleDto = RoleDto.builder().id(1L).name("name").build();
    private final UserAuthWithUserProfileDto userAuthWithUserProfileDto = UserAuthWithUserProfileDto.builder().
            id(1L).login("login").password("password").userProfileDto(UserProfileDto.builder().id(1L).name("name").build()).build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        when(userAuthRepository.getByLoginWithRole(anyString())).thenReturn(userAuth);
        UserDetails result = userAuthServiceImpl.loadUserByUsername(userAuthDto.getLogin());
        Assertions.assertEquals("login", result.getUsername());
    }

    @Test
    void testDeleteUserAuthById() {
        userAuthServiceImpl.deleteUserAuthById(1L);
        verify(userAuthRepository,times(1)).deleteUserAuth(1L);
    }

    @Test
    void testCreateUserAuth() {
        when(userAuthRepository.save(any())).thenReturn(userAuth);
        when(mapper.convert(userAuth, UserAuthWithRoleDto.class)).thenReturn(userAuthWithRoleDto);
        when(mapper.convert(role,RoleDto.class)).thenReturn(roleDto);
        when(roleRepository.getRoleByName(any())).thenReturn(role);
        UserAuthWithRoleDto result = userAuthServiceImpl.createUserAuth(userAuthCreateDto);
        Assertions.assertEquals(userAuthWithRoleDto, result);
    }

    @Test
    void testGetUserAuthById() {
        when(userAuthRepository.getById(any())).thenReturn(userAuth);
        when(mapper.convert(userAuth, UserAuthDto.class)).thenReturn(userAuthDto);
        UserAuthDto result = userAuthServiceImpl.getUserAuthById(userAuthDto.getId());
        Assertions.assertEquals(userAuthDto, result);
        verify(userAuthRepository,times(1)).getById(userAuthDto.getId());
    }

    @Test
    void testGetAllUsersAuths() {
        when(userAuthRepository.getAll(any())).thenReturn(Collections.singletonList(userAuth));
        when(mapper.convert(any(), any())).thenReturn(Collections.singletonList(userAuthDto));
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Page result = userAuthServiceImpl.getAllUsersAuths(pageable);
        Assertions.assertEquals(1L, result.getContent().size());
        Assertions.assertEquals(userAuthDto, result.getContent().get(0));
        verify(userAuthRepository,times(1)).getAll(pageable);
    }

    @Test
    void testUpdateUserAuth() {
        when(userAuthRepository.getByIdWithRole(anyLong())).thenReturn(userAuth);
        when(userAuthRepository.update(any())).thenReturn(userAuth);
        when(mapper.convert(userAuth, UserAuthDto.class)).thenReturn(userAuthDto);
        when(roleRepository.update(any())).thenReturn(role);
        UserAuthDto result = userAuthServiceImpl.updateUserAuth(userAuthDto);
        Assertions.assertEquals(userAuthDto, result);
        verify(userAuthRepository,times(1)).update(userAuth);
    }

    @Test
    void testGetUserAuthByLogin() {
        when(userAuthRepository.getByLogin(anyString())).thenReturn(userAuth);
        when(mapper.convert(userAuth, UserAuthDto.class)).thenReturn(userAuthDto);
        UserAuthDto result = userAuthServiceImpl.getUserAuthByLogin(userAuthDto.getLogin());
        Assertions.assertEquals(userAuthDto, result);
        verify(userAuthRepository,times(1)).getByLogin(userAuthDto.getLogin());
    }

    @Test
    void testGetUserAuthWithUserProfile() {
        when(userAuthRepository.getUserAuthWithUserProfile(anyLong())).thenReturn(userAuth);
        when(mapper.convert(userAuth, UserAuthWithUserProfileDto.class)).thenReturn(userAuthWithUserProfileDto);
        when(mapper.convert(userAuth.getUserProfile(),UserProfileDto.class)).
                thenReturn(userAuthWithUserProfileDto.getUserProfileDto());
        UserAuthWithUserProfileDto result = userAuthServiceImpl.getUserAuthWithUserProfile(1L);
        Assertions.assertEquals(userAuthWithUserProfileDto, result);
        verify(userAuthRepository,times(1)).getUserAuthWithUserProfile(userAuthDto.getId());
    }
}
