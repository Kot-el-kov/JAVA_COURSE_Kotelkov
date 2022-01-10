package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.store.StoreCreateDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.dto.store.StoreWithProductsDto;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public StoreDto createStore(StoreCreateDto storeCreateDto) {
        return mapper.convert(storeRepository.save(mapper.convert(storeCreateDto,Store.class)),StoreDto.class);
    }

    @Transactional
    @Override
    public StoreDto getStoreById(Long id) {
        return Optional.ofNullable(mapper.convert(storeRepository.getById(id), StoreDto.class)).
                orElseThrow(()-> new ResourceNotFoundException("Store with id: "+id+" not found"));
    }

    @Transactional
    @Override
    public Page getAllStores(Pageable pageable) {
        List storeList = storeRepository.getAll(pageable);
        List storeDtoList = Optional.ofNullable(mapper.convert(storeList,StoreDto.class)).
                orElseThrow(()->new ResourceNotFoundException("Stores not found"));
        return new PageImpl(storeDtoList,pageable,storeDtoList.size());
    }

    @Transactional
    @Override
    public StoreDto updateStore(StoreDto storeDto) {
        return mapper.convert(storeRepository.update(mapper.convert(storeDto,Store.class)),StoreDto.class);
    }

    @Transactional
    @Override
    public void deleteStore(Long id) {
        storeRepository.delete(id);
    }

    @Transactional
    @Override
    public StoreWithProductsDto getStoreWithProducts(Long id) {
        Store store = storeRepository.getStoreWithProducts(id);
        StoreWithProductsDto storeDto = mapper.convert(store,StoreWithProductsDto.class);
        List productList = store.getProducts();
        storeDto.setProductsDto(mapper.convert(productList,ProductDto.class));
        return storeDto;
    }

    @Transactional
    @Override
    public StoreDto getStoreByName(String name) {
        return mapper.convert(storeRepository.getStoreByName(name),StoreDto.class);
    }
}
