package com.github.kotelkov.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreWithProductsDto {
    private Long id;
    private String name;
    private String address;
    private List<ProductDto> productsDto;
}