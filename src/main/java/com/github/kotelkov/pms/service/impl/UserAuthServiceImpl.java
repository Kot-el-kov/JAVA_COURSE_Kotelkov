package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.dao.impl.RoleRepositoryImpl;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthCreateDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithRoleDto;
import com.github.kotelkov.pms.dto.user.auth.UserAuthWithUserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAuthServiceImpl implements UserAuthService,UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepositoryImpl roleRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserAuth user = Optional.ofNullable(userAuthRepository.getByLoginWithRole(login)).
                orElseThrow(()->new ResourceNotFoundException(login +" not found"));
        return new User(user.getLogin(),user.getPassword(),Optional.
                ofNullable(user.getRole()).
                map(Role::getName).
                map(x-> List.of(new SimpleGrantedAuthority("ROLE_" + x))).
                orElse(Collections.emptyList()));
    }

    @Transactional
    @Override
    public void deleteUserAuthById(Long id) {
        userAuthRepository.deleteUserAuth(id);
    }

    @Transactional
    @Override
    public UserAuthWithRoleDto createUserAuth(UserAuthCreateDto userAuthCreateDto) {
        String role = userAuthCreateDto.getRole().toUpperCase(Locale.ROOT);
        UserAuth userAuth = new UserAuth(userAuthCreateDto.getLogin(),
                passwordEncoder.encode(userAuthCreateDto.getPassword()),roleRepository.getRoleByName(role));
        userAuth = userAuthRepository.save(userAuth);
        return mapper.convert(userAuth,UserAuthWithRoleDto.class);
    }

    @Transactional
    @Override
    public UserAuthDto getUserAuthById(Long id) {
        return Optional.ofNullable(mapper.convert(userAuthRepository.getById(id),UserAuthDto.class)).
                orElseThrow(()->new ResourceNotFoundException("UserAuth with id: "+id+" not found"));
    }

    @Transactional
    @Override
    public Page getAllUsersAuths(Pageable pageable) {
        List userAuthList = userAuthRepository.getAll(pageable);
        List userAuthDtoList = mapper.convert(userAuthList,UserAuthDto.class);
        return new PageImpl(userAuthDtoList,pageable,userAuthDtoList.size());
    }

    @Transactional
    @Override
    public UserAuthDto updateUserAuth(UserAuthDto userAuthDto) {
        UserAuth userAuth = userAuthRepository.getByIdWithRole(userAuthDto.getId());
        userAuth.setLogin(userAuthDto.getLogin());
        userAuth.setPassword(userAuthDto.getPassword());
        return mapper.convert(userAuthRepository.update(userAuth),UserAuthDto.class);
    }

    @Transactional
    @Override
    public UserAuthDto getUserAuthByLogin(String login){
        return mapper.convert(userAuthRepository.getByLogin(login),UserAuthDto.class);
    }

    @Transactional
    @Override
    public UserAuthWithUserProfileDto getUserAuthWithUserProfile(Long id) {
        UserAuth userAuth = userAuthRepository.getUserAuthWithUserProfile(id);
        UserAuthWithUserProfileDto userAuthWithUserProfileDto = mapper.convert(userAuth,UserAuthWithUserProfileDto.class);
        Optional.ofNullable(userAuthWithUserProfileDto.getUserProfile()).
                orElseThrow(()->new ResourceNotFoundException("This user does not have profile"));
        return userAuthWithUserProfileDto;
    }
}
