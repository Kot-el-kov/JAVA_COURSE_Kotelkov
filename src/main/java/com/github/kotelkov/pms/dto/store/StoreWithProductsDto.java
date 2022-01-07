package com.github.kotelkov.pms.dto.store;

import com.github.kotelkov.pms.dto.product.ProductDto;
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
public class StoreWithProductsDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(min=4,max=15)
    private String name;
    @NotNull
    @Size(min=4,max=15)
    private String address;
    @NotNull
    private List<ProductDto> productsDto;
}