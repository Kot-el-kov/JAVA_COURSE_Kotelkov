package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileCreateDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithHistoryDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithWishlistDto;
import com.github.kotelkov.pms.entity.UserProfile;
import com.github.kotelkov.pms.mapper.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

public class UserProfileServiceImplTest {
    @Mock
    UserProfileRepository userProfileRepository;
    @Mock
    Mapper mapper;
    @InjectMocks
    UserProfileServiceImpl userProfileServiceImpl;

    private final ProductDto productDto = ProductDto.builder().id(1L).name("name").price(100).description("description").build();
    private final UserProfile userProfile = UserProfile.builder().id(1L).
            name("name").surname("surname").email("asda@mail.ru").build();
    private final UserProfileDto userProfileDto = UserProfileDto.builder().id(1L).
            name("name").surname("surname").email("asda@mail.ru").build();
    private final UserProfileCreateDto userProfileCreateDto = UserProfileCreateDto.builder().name("name").
            surname("surname").email("asda@mail.ru").build();
    private final UserProfileWithHistoryDto userProfileWithHistoryDto = UserProfileWithHistoryDto.builder().
            id(1L).name("name").surname("surname").email("asda@mail.ru").history(Collections.singletonList(productDto)).build();
    private final UserProfileWithWishlistDto userProfileWithWishlistDto = UserProfileWithWishlistDto.builder().
            id(1L).name("name").surname("surname").email("asda@mail.ru").wishlist(Collections.singletonList(productDto)).build();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUserProfile() {
        when(userProfileRepository.save(any())).thenReturn(userProfile);
        when(mapper.convertToModel(any(), any())).thenReturn(userProfile);
        when(mapper.convertToDto(any(), any())).thenReturn(userProfileDto);
        UserProfileDto result = userProfileServiceImpl.createUserProfile(1L,userProfileCreateDto);
        Assert.assertEquals(userProfileDto, result);
        verify(userProfileRepository,times(1)).save(userProfile);
    }

    @Test
    void testGetUserProfileById() {
        when(userProfileRepository.getById(any())).thenReturn(userProfile);
        when(mapper.convertToDto(any(), any())).thenReturn(userProfileDto);
        UserProfileDto result = userProfileServiceImpl.getUserProfileById(userProfile.getId());
        Assert.assertEquals(userProfileDto, result);
        verify(userProfileRepository,times(1)).getById(userProfile.getId());
    }

    @Test
    void testGetAllUsersProfiles() {
        when(userProfileRepository.getAll(any())).thenReturn(Collections.singletonList(userProfile));
        when(mapper.convertListToDtoList(any(), any())).thenReturn(Collections.singletonList(userProfileDto));
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Page result = userProfileServiceImpl.getAllUsersProfiles(pageable);
        Assert.assertEquals(1, result.getContent().size());
        Assert.assertEquals(userProfileDto, result.getContent().get(0));
        verify(userProfileRepository,times(1)).getAll(pageable);
    }

    @Test
    void testUpdateUserProfile() {
        when(userProfileRepository.update(any())).thenReturn(userProfile);
        when(mapper.convertToModel(any(), any())).thenReturn(userProfile);
        when(mapper.convertToDto(any(), any())).thenReturn(userProfileDto);
        UserProfileDto result = userProfileServiceImpl.updateUserProfile(1L, userProfileCreateDto);
        Assert.assertEquals(userProfileDto, result);
        verify(userProfileRepository,times(1)).update(userProfile);
    }

    @Test
    void testDeleteUserProfileById() {
        userProfileServiceImpl.deleteUserProfileById(1L);
        verify(userProfileRepository,times(1)).delete(1L);
    }

    @Test
    void testGetUserProfileWithHistory() {
        when(userProfileRepository.getUserProfileWithHistory(anyLong())).thenReturn(userProfile);
        when(mapper.convertToDto(any(), any())).thenReturn(userProfileWithHistoryDto);
        UserProfileWithHistoryDto result = userProfileServiceImpl.getUserProfileWithHistory(1L);
        Assert.assertEquals(userProfileWithHistoryDto, result);
        verify(userProfileRepository,times(1)).getUserProfileWithHistory(1L);
    }

    @Test
    void testGetUserProfileWithWishlist() {
        when(userProfileRepository.getUserProfileWithWishlist(anyLong())).thenReturn(userProfile);
        when(mapper.convertToDto(any(), any())).thenReturn(userProfileWithWishlistDto);
        UserProfileWithWishlistDto result = userProfileServiceImpl.getUserProfileWithWishlist(1L);
        Assert.assertEquals(userProfileWithWishlistDto, result);
        verify(userProfileRepository,times(1)).getUserProfileWithWishlist(1L);
    }

    @Test
    void testClearWishlist() {
        userProfileServiceImpl.clearWishlist(1L);
        verify(userProfileRepository,timeout(1)).clearWishlist(1L);
    }
}
