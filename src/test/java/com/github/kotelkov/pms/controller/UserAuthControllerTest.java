package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.WebApplicationTest;
import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthCreateDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithRoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithUserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.entity.UserProfile;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserAuthService;
import com.github.kotelkov.pms.service.impl.UserAuthServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserAuthControllerTest extends WebApplicationTest {

    @Autowired
    UserAuthRepository userAuthRepository;
    @Autowired
    RoleRepository roleRepository;

    /*@Test
    void testCreateUserAuth() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Assertions.assertEquals(0, userAuthRepository.getAll(pageable).size());
        final Role role = roleRepository.save(Role.builder().name("role").build());

        when(userAuthServiceImpl.createUserAuth(any())).thenReturn(UserAuthWithRoleDto.builder().
                roleDto(RoleDto.builder().id(role.getId()).name(role.getName()).build()).
                login("login").password("password").build());

        final String userAuthDtoJson =
                """  
                {
                   "role":"role",
                   "login": "login",
                   "password":"password"
                }
                """;

        mockMvc.perform(post("/users_auths").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(userAuthDtoJson)).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").exists()).
                andExpect(jsonPath("$.login").value("login"));

        assertNotNull(userAuthRepository.getAll(pageable));
    }*/

    @Test
    void testGetUserAuthById() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("role").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().role(role).login("login").password("password").build());
        mockMvc.perform(get("/users_auths/"+userAuth.getId())).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").value(userAuth.getId())).
                andExpect(jsonPath("$.login").value(userAuth.getLogin()));
    }

    @Test
    void testGetAllUsersAuths() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("role").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().role(role).login("login").password("password").build());
        mockMvc.perform(get("/users_auths")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateUserAuth() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("role").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().role(role).login("login").password("password").build());

        final String userAuthUpdatedDto = String.format("""
                {
                   "login": "updated",
                   "password":"password",
                   "id": %s
                }
                """, userAuth.getId());

        mockMvc.perform(put("/users_auths/").
                contentType(MediaType.APPLICATION_JSON).
                content(userAuthUpdatedDto)).
                andExpect(status().is2xxSuccessful()).
                andDo(print()).
                andExpect(jsonPath("$.id").value(userAuth.getId())).
                andExpect(jsonPath("$.login").value("updated"));
    }

    @Test
    void testDeleteUserAuthById() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("role").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().role(role).login("login").password("password").build());

        mockMvc.perform(delete("/users_auths/" + userAuth.getId())).
                andExpect(status().is2xxSuccessful());

        final UserAuth userAuth1 = userAuthRepository.getById(userAuth.getId());

        assertNull(userAuth1);
    }

    @Test
    void testGetUserAuthWithUserProfile() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().login("login").password("password").
                role(role).build());
        mockMvc.perform(get("/users_auths/profile/"+userAuth.getId())).
                andExpect(jsonPath("$.message").
                value("This user does not have profile"));

    }
}