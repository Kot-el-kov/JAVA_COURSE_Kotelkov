package com.github.kotelkov.pms.dto.role;

import com.github.kotelkov.pms.dto.user.auth.UserAuthDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleWithUserAuthDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(min=3,max=10)
    private String name;
    @NotNull
    private List<UserAuthDto> userAuthList;
}
