package com.github.kotelkov.pms.dto.user.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(min=4,max=15)
    private String login;
    @NotNull
    @Size(min=4,max=15)
    private String password;
}