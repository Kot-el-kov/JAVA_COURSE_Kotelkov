package com.github.kotelkov.pms.service.impl;


import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = Optional.ofNullable(userAuthRepository.getByLoginWithRole(username)).
                orElseThrow(()->new ResourceNotFoundException(username +" not found"));

        return new User(user.getLogin(),passwordEncoder.encode(user.getPassword()),Optional.
                ofNullable(user.getRole()).
                map(Role::getName).
                map(x-> List.of(new SimpleGrantedAuthority("ROLE_" + x))).
                orElse(Collections.emptyList()));
    }
}
