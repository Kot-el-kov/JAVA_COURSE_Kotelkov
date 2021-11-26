package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.StoreDto;

import java.util.List;

public interface StoreService {
    void createStore(StoreDto storeDto);
    StoreDto getStoreById(Long id);
    List<StoreDto> getAllStores();
    void updateStore(StoreDto storeDto);
    void deleteStore(Long id);
    StoreDto getByIdWithProductsCriteria(Long id);
    StoreDto getByIdWithProductsJPQL(Long id);
    StoreDto getByIdWithProductsGraph(Long id);
}
