package com.github.kotelkov.pms.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDtoWithStores {
    private Long id;
    private String name;
    private int price;
    private String description;
    private List<StoreDto> storesDto;
}
