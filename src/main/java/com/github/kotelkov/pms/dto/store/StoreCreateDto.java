package com.github.kotelkov.pms.dto.store;

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
public class StoreCreateDto {
    @NotNull
    @Size(min=4,max=15)
    private String name;
    @NotNull
    @Size(min=4,max=15)
    private String address;
}