package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.store.StoreCreateDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.dto.store.StoreWithProductsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {
    StoreDto createStore(StoreCreateDto storeCreateDto);
    StoreDto getStoreById(Long id);
    Page<StoreDto> getAllStores(Pageable pageable);
    StoreDto updateStore(StoreDto storeDto);
    void deleteStore(Long id);
    StoreWithProductsDto getStoreWithProducts(Long id);
    StoreDto getStoreByName(String name);
}
