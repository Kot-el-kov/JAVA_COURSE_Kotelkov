package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.dto.UserAuthDto;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public UserAuthDto createUserAuth(UserAuthDto userAuthDto) {
        return (UserAuthDto) mapper.convertToDto(userAuthRepository.
                save((UserAuth) mapper.convertToModel(userAuthDto, UserAuth.class)),UserAuthDto.class);
    }

    @Override
    public UserAuthDto getUserAuthById(Long id) {
        return (UserAuthDto) mapper.convertToDto(userAuthRepository.getById(id),UserAuthDto.class);
    }

    @Override
    public List<UserAuthDto> getAllUsersAuths() {
        return mapper.convertListToDtoList(userAuthRepository.getAll(),UserAuthDto.class);
    }

    @Override
    public UserAuthDto updateUserAuth(UserAuthDto userAuthDto) {
        return (UserAuthDto) mapper.convertToDto(userAuthRepository.update((UserAuth)
                mapper.convertToModel(userAuthDto,UserAuth.class)),UserAuthDto.class);
    }

    @Override
    public void deleteUserAuth(Long id) {
        userAuthRepository.delete(id);
    }

}
