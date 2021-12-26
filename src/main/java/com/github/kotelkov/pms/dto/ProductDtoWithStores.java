package com.github.kotelkov.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoWithStores {
    private Long id;
    private String name;
    private int price;
    private String description;
    private List<StoreDto> storesDto;
}
