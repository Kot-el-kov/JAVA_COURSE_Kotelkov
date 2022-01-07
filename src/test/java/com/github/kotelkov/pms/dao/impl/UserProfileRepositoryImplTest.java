package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.BaseRepositoryTest;
import com.github.kotelkov.pms.dao.*;
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
class UserProfileRepositoryImplTest extends BaseRepositoryTest {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserAuthRepository userAuthRepository;
    @Autowired
    RoleRepository roleRepository;

    private final UserProfile userProfile = UserProfile.builder().id(1L).name("name").
            surname("surname").email("asd@asd.ru").build();


    @BeforeEach
    void init() {
        roleRepository.save(Role.builder().name("name").build());
        userAuthRepository.save(UserAuth.builder().login("login").password("password").
                role(Role.builder().id(1L).name("name").build()).build());
        userProfileRepository.save(userProfile);
    }

    @Test
    void testGetUserProfileWithHistory() {
        UserProfile result = userProfileRepository.getUserProfileWithHistory(userProfile.getId());
        Assertions.assertEquals("[]",result.getHistory().toString());
    }

    @Test
    void testClearWishlist() {
        userProfileRepository.clearWishlist(userProfile.getId());
        UserProfile result = userProfileRepository.getUserProfileWithWishlist(userProfile.getId());
        Assertions.assertEquals("[]",result.getWishlist().toString());
    }

    @Test
    void testGetUserProfileWithWishlist() {
        UserProfile result = userProfileRepository.getUserProfileWithWishlist(userProfile.getId());
        Assertions.assertEquals("[]",result.getWishlist().toString());
    }

    @Test
    void testGetById() {
        UserProfile result = userProfileRepository.getById(userProfile.getId());
        Assertions.assertEquals(userProfile.getId(),result.getId());
        Assertions.assertEquals(userProfile.getName(),result.getName());
    }

    @Test
    void testUpdate() {
        UserProfile result = userProfileRepository.update(userProfile);
        Assertions.assertEquals(userProfile.getId(),result.getId());
        Assertions.assertEquals(userProfile.getName(),result.getName());

    }

    @Test
    void testDelete() {
        userProfileRepository.delete(userProfile.getId());
        UserProfile result = userProfileRepository.getById(userProfile.getId());
        Assertions.assertNull(result);
    }

    @Test
    void testGetAll() {
        List<UserProfile> result = userProfileRepository.getAll(PageRequest.of(0, 10, ASC,"id"));
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals(userProfile.getId(),result.get(0).getId());
        Assertions.assertEquals(userProfile.getName(),result.get(0).getName());
    }
}
