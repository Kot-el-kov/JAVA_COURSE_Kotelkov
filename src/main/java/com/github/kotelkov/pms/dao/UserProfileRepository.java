package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.UserProfile;

public interface UserProfileRepository extends GenericDao<UserProfile, Long> {
    UserProfile getUserProfileWithWishlist(Long id);
    UserProfile getUserProfileWithHistory(Long id);
    void clearWishlist(Long id);
}
