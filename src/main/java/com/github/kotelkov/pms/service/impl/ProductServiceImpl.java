package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dto.product.ProductCreateDto;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.product.ProductWithStoresDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class  ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        return mapper.convert(productRepository.save(mapper.convert(productCreateDto,Product.class)),ProductDto.class);
    }

    @Transactional
    @Override
    public ProductDto getProductById(Long userId,Long productId) {
        addProductToHistory(userId,productId);
        ProductDto productDto = mapper.convert(productRepository.getById(productId),ProductDto.class);
        return Optional.ofNullable(productDto).
                orElseThrow(() -> new ResourceNotFoundException("Product with id: "+productId+" not found"));
    }

    @Transactional
    @Override
    public Page getAllProducts(Pageable pageable) {
        List productList = productRepository.getAll(pageable);
        List productDtoList = mapper.convert(productList,ProductDto.class);
        Optional.ofNullable(productDtoList).orElseThrow(()-> new ResourceNotFoundException("Products not found"));
        return new PageImpl(productDtoList,pageable.first() ,productDtoList.size());
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return mapper.convert(productRepository.update(mapper.convert(productDto,Product.class)),ProductDto.class);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Transactional
    @Override
    public ProductWithStoresDto getProductWithStores(Long productId) {
        Product product = productRepository.getProductWithStores(productId);
        ProductWithStoresDto productWithStoresDto = mapper.convert(product, ProductWithStoresDto.class);
        return Optional.of(productWithStoresDto).
                orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    @Transactional
    @Override
    public void addProductToStore(Long productId, Long storeId) {
        productRepository.addProductToStore(productId,storeId);
    }

    @Transactional
    @Override
    public void addProductToHistory(Long userId,Long productId){
        productRepository.addProductToHistory(userId, productId);
    }

    @Transactional
    @Override
    public void addProductToWishlist(Long userId,Long productId){
        productRepository.addProductToWishlist(userId, productId);
    }

    @Transactional
    @Override
    public Page getProductsByName(String name,Pageable pageable){
        List productList = productRepository.getProductsByName(name,pageable);
        List productDtoList = mapper.convert(productList,ProductDto.class);
        return new PageImpl(productDtoList,pageable.first(),productDtoList.size());
    }

    @Transactional
    @Override
    public void deleteProductFromWishlist(Long id, Long productId) {
        productRepository.deleteProductFromWishlist(id,productId);
    }

    @Transactional
    @Override
    public void deleteProductFromStore(Long productId, Long storeId) {
        productRepository.deleteProductFromStore(productId,storeId);
    }
}
