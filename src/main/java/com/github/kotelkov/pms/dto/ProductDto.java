package com.github.kotelkov.pms.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private String description;
    private List<StoreDto> storesDto;
}
