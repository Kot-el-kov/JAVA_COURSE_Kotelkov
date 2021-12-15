package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.StoreDto;
import com.github.kotelkov.pms.dto.StoreWithProductsDto;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto);
    StoreDto getStoreById(Long id);
    List<StoreDto> getAllStores();
    StoreDto updateStore(StoreDto storeDto);
    void deleteStore(Long id);
    StoreWithProductsDto getByIdWithProductsCriteria(Long id);
    StoreDto getByIdWithProductsJPQL(Long id);
    StoreDto getByIdWithProductsGraph(Long id);
}
