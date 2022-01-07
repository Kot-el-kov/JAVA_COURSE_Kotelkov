package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.store.StoreCreateDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.dto.store.StoreWithProductsDto;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public StoreDto createStore(StoreCreateDto storeCreateDto) {
        return (StoreDto) mapper.convertToDto(storeRepository.
                save((Store) mapper.convertToModel(storeCreateDto,Store.class)),StoreDto.class);
    }

    @Transactional
    @Override
    public StoreDto getStoreById(Long id) {
        return (StoreDto) mapper.convertToDto(storeRepository.getById(id), StoreDto.class);
    }

    @Transactional
    @Override
    public Page getAllStores(Pageable pageable) {
        List storeDtoList = mapper.convertListToDtoList(storeRepository.getAll(pageable),StoreDto.class);
        return new PageImpl(storeDtoList,pageable,storeDtoList.size());

    }

    @Transactional
    @Override
    public StoreDto updateStore(StoreDto storeDto) {
        return (StoreDto) mapper.convertToDto(storeRepository.update((Store)
                mapper.convertToModel(storeDto,Store.class)),StoreDto.class);
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
        StoreWithProductsDto storeDto = (StoreWithProductsDto) mapper.convertToDto(store,StoreWithProductsDto.class);
        storeDto.setProductsDto(mapper.convertListToDtoList(store.getProducts(),ProductDto.class));
        return storeDto;
    }

    @Transactional
    @Override
    public StoreDto getStoreByName(String name) {
        return (StoreDto) mapper.convertToDto(storeRepository.getStoreByName(name),StoreDto.class);
    }
}
