package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.user.profile.UserProfileCreateDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithHistoryDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithWishlistDto;
import com.github.kotelkov.pms.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users_profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @PostMapping
    public UserProfileDto createUserProfile(@AuthenticationPrincipal String userId,@Valid @RequestBody UserProfileCreateDto userProfileCreateDto) {
        return userProfileService.createUserProfile(Long.parseLong(userId),userProfileCreateDto);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/id")
    public UserProfileDto getUserProfileById(@AuthenticationPrincipal String userId) {
        return userProfileService.getUserProfileById(Long.parseLong(userId));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public Page<UserProfileDto> getAllUsersProfiles(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                    @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
        return userProfileService.getAllUsersProfiles(PageRequest.of(page, size, ASC, sort));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @PutMapping
    public UserProfileDto updateUserProfile(@AuthenticationPrincipal String userId, @Valid @RequestBody UserProfileCreateDto userProfileCreateDto) {
        return userProfileService.updateUserProfile(Long.parseLong(userId),userProfileCreateDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUserProfileById(@PathVariable Long id) {
        userProfileService.deleteUserProfileById(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/history")
    public UserProfileWithHistoryDto getUserProfileWithHistory(@AuthenticationPrincipal String userId){
        return userProfileService.getUserProfileWithHistory(Long.parseLong(userId));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/wishlist")
    public UserProfileWithWishlistDto getUserProfileWithWishlist(@AuthenticationPrincipal String userId){
        return userProfileService.getUserProfileWithWishlist(Long.parseLong(userId));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @DeleteMapping("/wishlist")
    public void clearWishlist(@AuthenticationPrincipal String id) {
        userProfileService.clearWishlist(Long.parseLong(id));
    }
}
