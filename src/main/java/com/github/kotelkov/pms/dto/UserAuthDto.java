package com.github.kotelkov.pms.dto;

import lombok.Data;

@Data
public class UserAuthDto {
    private Long id;
    private String login;
    private String password;
}
