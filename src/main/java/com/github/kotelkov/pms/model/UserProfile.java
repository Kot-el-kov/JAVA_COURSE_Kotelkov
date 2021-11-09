package com.github.kotelkov.pms.model;

import lombok.Data;

@Data
public class UserProfile {
    private int id;
    private String name;
    private String surname;
    private String email;
}
