package com.github.kotelkov.pms.model;

import lombok.Data;

@Data
public class UserAuth {
    private int id;
    private String login;
    private String password;
}
