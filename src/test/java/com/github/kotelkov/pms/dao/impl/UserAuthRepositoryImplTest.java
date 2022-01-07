package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.BaseRepositoryTest;
import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@ContextConfiguration(classes = {UserProfileRepositoryImpl.class,UserAuthRepositoryImpl.class,RoleRepositoryImpl.class})
class UserAuthRepositoryImplTest extends BaseRepositoryTest {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    private final Role role = Role.builder().id(1L).name("name").build();
    private final UserProfile userProfile = UserProfile.builder().id(1L).name("name").
            surname("surname").email("asd@asd.ru").build();
    private final UserAuth userAuth = UserAuth.builder().id(1L).login("login").role(role).
            password("password").userProfile(userProfile).build();
    private final UserAuth userAuthCreate = UserAuth.builder().login("login").role(role).password("password").build();


    @BeforeEach
    void init(){
        roleRepository.save(Role.builder().name("name").build());
        userAuthRepository.save(userAuthCreate);
        userProfileRepository.save(userProfile);
    }

    @Test
    void testGetByLoginWithRole() {
        UserAuth result = userAuthRepository.getByLoginWithRole(userAuthCreate.getLogin());
        Assertions.assertEquals(userAuth.getLogin(),result.getLogin());
        Assertions.assertEquals(userAuth.getRole().getId(),result.getRole().getId());
        Assertions.assertEquals(userAuth.getRole().getName(),result.getRole().getName());
    }

    @Test
    void testGetByIdWithRole() {
        UserAuth result = userAuthRepository.getByIdWithRole(userAuth.getId());
        Assertions.assertEquals(userAuth.getRole().getId(),result.getRole().getId());
        Assertions.assertEquals(userAuth.getRole().getName(),result.getRole().getName());
    }

    @Test
    void testGetUserAuthWithUserProfile() {
        UserAuth result = userAuthRepository.getUserAuthWithUserProfile(userAuth.getId());
        Assertions.assertEquals(userAuth.getUserProfile().getId(),result.getUserProfile().getId());
        Assertions.assertEquals(userAuth.getUserProfile().getName(),result.getUserProfile().getName());
    }

    @Test
    void testGetByLogin() {
        UserAuth result = userAuthRepository.getByLogin(userAuthCreate.getLogin());
        Assertions.assertEquals(userAuth.getId(),result.getId());
        Assertions.assertEquals(userAuth.getLogin(),result.getLogin());
    }

    @Test
    void testDeleteUserAuth() {
        userAuthRepository.deleteUserAuth(userAuth.getId());
        UserAuth result = userAuthRepository.getById(userAuth.getId());
        Assertions.assertNull(result);
    }

    @Test
    void testUpdate() {
        UserAuth result = userAuthRepository.update(userAuth);
        Assertions.assertEquals(userAuth.getId(),result.getId());
        Assertions.assertEquals(userAuth.getLogin(),result.getLogin());
    }

    @Test
    void testDelete() {
        userAuthRepository.delete(userAuth.getId());
        UserAuth result = userAuthRepository.getById(userAuth.getId());
        Assertions.assertNull(result);
    }

    @Test
    void testGetAll() {
        List<UserAuth> result = userAuthRepository.getAll(PageRequest.of(0, 10, ASC,"id"));
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals(userAuth.getId(),result.get(0).getId());
        Assertions.assertEquals(userAuth.getLogin(),result.get(0).getLogin());
    }
}
