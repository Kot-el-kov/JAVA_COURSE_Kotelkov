package com.github.kotelkov.pms.dto.product;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(min=4,max=15)
    private String name;
    @Min(1)
    @Max(999999)
    private int price;
    @NotNull
    @Size(min=4,max=200)
    private String description;
}
