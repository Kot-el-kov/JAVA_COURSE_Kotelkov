package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dto.product.ProductCreateDto;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.product.ProductWithStoresDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class  ProductServiceImpl implements ProductService {

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
    public ProductDto getProductById(Long userId,Long productId) {
        addProductToHistory(userId,productId);
        return (ProductDto) mapper.convertToDto(productRepository.getById(productId),ProductDto.class);
    }

    @Transactional
    @Override
    public Page getAllProducts(Pageable pageable) {
        List productDtoList = mapper.convertListToDtoList(productRepository.getAll(pageable),ProductDto.class);
        return new PageImpl(productDtoList,pageable.first() ,productDtoList.size());
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
    public ProductWithStoresDto getProductWithStores(Long productId) {
        Product product = productRepository.getProductWithStores(productId);
        ProductWithStoresDto productWithStoresDto = (ProductWithStoresDto) mapper.convertToDto(product, ProductWithStoresDto.class);
        List storeDtoList = mapper.convertListToDtoList(product.getStores(), StoreDto.class);
        productWithStoresDto.setStoresDto(storeDtoList);
        return productWithStoresDto;
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
        List productDtoList = mapper.convertListToDtoList(productRepository.
                getProductsByName(name,pageable),ProductDto.class);
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
