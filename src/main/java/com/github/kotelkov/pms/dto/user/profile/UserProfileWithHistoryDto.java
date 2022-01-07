package com.github.kotelkov.pms.dto.user.profile;

import com.github.kotelkov.pms.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileWithHistoryDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(min=4,max=15)
    private String name;
    @NotNull
    @Size(min=4,max=15)
    private String surname;
    @NotNull
    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    private String email;
    @NotNull
    private List<ProductDto> history;
}
