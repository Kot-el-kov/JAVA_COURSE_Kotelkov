package com.github.kotelkov.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthWithRoleDto {
    private Long id;
    private String login;
    private String password;
    private RoleDto roleDto;
}
