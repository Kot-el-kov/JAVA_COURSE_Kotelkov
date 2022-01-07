package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.WebApplicationTest;
import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.entity.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserProfileControllerTest extends WebApplicationTest {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserAuthRepository userAuthRepository;
    @Autowired
    RoleRepository roleRepository;

    /*@Test
    void testCreateUserProfile() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().role(role).login("login").
                password("password").build());
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Assertions.assertEquals(0, userProfileRepository.getAll(pageable).size());


        final String userProfileDtoJson = String.format(
                """  
                {
                   "name": "name",
                   "surname":"surname",
                   "email":"asd@asd.ru",
                   "id": %s
                }
                """,  userAuth.getId());

        mockMvc.perform(post("/users_profiles/").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(userProfileDtoJson)).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").exists()).
                andExpect(jsonPath("$.name").value("name"));

        assertNotNull(userProfileRepository.getAll(pageable));
    }*/

    /*@Test
    void testGetUserProfileById() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().login("login").password("password").
                role(role).build());
        final UserProfile userProfile = userProfileRepository.save(UserProfile.builder().id(userAuth.getId()).name("name").
                surname("surname").email("asd@asd.ru").build());

        mockMvc.perform(get("/users_profiles/"+userProfile.getId())).
                //andExpect(status().is2xxSuccessful()).
                //andExpect(jsonPath("$.id").value(userProfile.getId())).
                andExpect(jsonPath("$.name").value(userProfile.getName()));
    }*/

    @Test
    void testGetAllUsersProfiles() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().login("login").password("password").
                role(role).build());
        userProfileRepository.save(UserProfile.builder().id(userAuth.getId()).name("name").
                surname("surname").email("asd@asd.ru").build());
        mockMvc.perform(get("/users_profiles")).
                andExpect(status().is2xxSuccessful());
    }

    /*@Test
    void testUpdateUserProfile() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().login("login").password("password").
                role(role).build());
        final UserProfile userProfile = userProfileRepository.save(UserProfile.builder().id(userAuth.getId()).name("name").
                surname("surname").email("asd@asd.ru").build());

        final String userProfileUpdatedDto = String.format("""
                {
                   "name": "updated",
                   "surname":"address",
                   "id": %s
                }
                """, userProfile.getId());

        mockMvc.perform(put("/users_profiles/").
                contentType(MediaType.APPLICATION_JSON).
                content(userProfileUpdatedDto)).
                andExpect(status().is2xxSuccessful()).
                andDo(print()).
                andExpect(jsonPath("$.id").value(userProfile.getId())).
                andExpect(jsonPath("$.name").value("updated"));
    }*/

    @Test
    void testDeleteUserProfileById() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        final UserAuth userAuth = userAuthRepository.save(UserAuth.builder().login("login").password("password").
                role(role).build());
        final UserProfile userProfile = userProfileRepository.save(UserProfile.builder().id(userAuth.getId()).name("name").
                surname("surname").email("asd@asd.ru").build());

        mockMvc.perform(delete("/users_profiles/" + userProfile.getId())).
                andExpect(status().is2xxSuccessful());

        final UserProfile userProfile1 = userProfileRepository.getById(userProfile.getId());

        assertNull(userProfile1);
    }

    /*@Test
    void testGetUserProfileWithHistory() throws Exception {
        mockMvc.perform(get("/users_profiles/history")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testGetUserProfileWithWishlist() {

    }

    @Test
    void testClearWishlist() {

    }*/
}