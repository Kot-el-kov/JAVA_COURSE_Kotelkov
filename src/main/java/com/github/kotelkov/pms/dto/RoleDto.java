package com.github.kotelkov.pms.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private List<UserAuthDto> userAuthsDto;
}
