package com.github.kotelkov.pms.dto;

import lombok.Data;

import java.util.List;

@Data
public class StoreDto {
    private Long id;
    private String name;
    private String address;
    private List<ProductDto> productsDto;
}
