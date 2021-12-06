package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dto.ProductCreateDto;
import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public ProductDto createProduct(ProductCreateDto productDto) {
        return (ProductDto) mapper.convertToDto(productRepository.
                save((Product) mapper.convertToModel(productDto,Product.class)),ProductDto.class);
    }

    @Transactional
    @Override
    public ProductDto getProductById(Long id) {
        return (ProductDto) mapper.convertToDto(productRepository.getById(id),ProductDto.class);
    }

    @Transactional
    @Override
    public List<ProductDto> getAllProducts() {
        return mapper.convertListToDtoList(productRepository.getAll(),ProductDto.class);
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return (ProductDto) mapper.convertToDto(productRepository.update((Product)
                mapper.convertToModel(productDto,Product.class)),ProductDto.class);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Transactional
    @Override
    public List<ProductDto> getProductSortedByPrice() {
        return mapper.convertListToDtoList(productRepository.getProductsSortedByPrice(),ProductDto.class);
    }
}
