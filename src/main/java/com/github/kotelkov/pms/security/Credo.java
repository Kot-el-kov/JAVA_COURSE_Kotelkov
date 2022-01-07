package com.github.kotelkov.pms.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credo {
    private Long id;
    private String login;
    private String password;
}
