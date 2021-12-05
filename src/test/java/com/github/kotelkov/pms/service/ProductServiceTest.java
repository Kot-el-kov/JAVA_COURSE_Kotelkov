package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dao.impl.ProductRepositoryImpl;
import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private Mapper mapper;

    @Mock
    private ProductRepositoryImpl productRepository;

    private final Product product = Product.builder().id(1L).name("name").price(100).description("description").build();
    private final ProductDto productDto = ProductDto.builder().id(1L).name("name").price(100).description("description").build();

    @Test
    public void createProduct(){
        when(productRepository.save(any())).thenReturn(product);
        when(mapper.convertToDto(any(Product.class),eq(ProductDto.class))).thenReturn(productDto);
        final ProductDto productDto1 = productService.createProduct(productDto);
        assertEquals(productDto,productDto1);
    }

    @Test
    public void getByIdProduct(){
        when(productRepository.getById(any())).thenReturn(product);
        when(mapper.convertToDto(any(Product.class),eq(ProductDto.class))).thenReturn(productDto);
        final ProductDto productDto1 = productService.getProductById(1L);

        assertEquals(productDto,productDto1);
        verify(productRepository,times(1)).getById(1L);
    }

    @Test
    public void updateProduct(){
        when(mapper.convertToModel(any(ProductDto.class),eq(Product.class))).thenReturn(product);
        when(productRepository.update(any())).thenReturn(product);
        when(mapper.convertToDto(any(Product.class),eq(ProductDto.class))).thenReturn(productDto);
        final ProductDto productDto1 = productService.updateProduct(productDto);
        assertEquals(productDto,productDto1);
        verify(productRepository,times(1)).update(product);

    }

    @Test
    public void deleteProduct(){
        doNothing().when(productRepository).delete(any());
        productService.deleteProduct(1L);
        verify(productRepository,times(1)).delete(1L);
    }
}
