package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.user.profile.UserProfileCreateDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithHistoryDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithWishlistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService {
    UserProfileDto createUserProfile(Long userId,UserProfileCreateDto userProfileCreateDtov);
    UserProfileDto getUserProfileById(Long id);
    Page<UserProfileDto> getAllUsersProfiles(Pageable pageable);
    UserProfileDto updateUserProfile(Long userId,UserProfileCreateDto userProfileCreateDto);
    void deleteUserProfileById(Long id);
    UserProfileWithHistoryDto getUserProfileWithHistory(Long id);
    UserProfileWithWishlistDto getUserProfileWithWishlist(Long id);
    void clearWishlist(Long id);
}
