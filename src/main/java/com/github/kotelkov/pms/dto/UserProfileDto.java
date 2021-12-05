package com.github.kotelkov.pms.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
