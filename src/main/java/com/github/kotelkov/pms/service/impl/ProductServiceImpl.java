package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
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
    public void createProduct(ProductDto productDto) {
        Product  product = new Product();
        product= (Product) mapper.convertToModel(productDto,Product.class);
        productRepository.save((Product) mapper.convertToModel(productDto,Product.class));
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
    public void updateProduct(ProductDto productDto) {
        productRepository.update((Product) mapper.convertToModel(productDto,Product.class));
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<ProductDto> getProductSortedByPrice() {
        return mapper.convertListToDtoList(productRepository.getProductsSortedByPrice(),ProductDto.class);
    }
}
