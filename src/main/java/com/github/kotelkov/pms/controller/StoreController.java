package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.StoreDto;
import com.github.kotelkov.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreController {

    @Autowired
    private StoreService storeService;
    
    public void createStore(StoreDto storeDto) {
        storeService.createStore(storeDto);
    }
    
    public StoreDto getStoreById(Long id) {
        return storeService.getStoreById(id);
    }
   
    public List<StoreDto> getAllStores() {
        return storeService.getAllStores();
    }
   
    public void updateStore(StoreDto storeDto) {
        storeService.updateStore(storeDto);
    }
    
    public void deleteStore(Long id) {
        storeService.deleteStore(id);
    }

    public StoreDto getByIdWithProductsCriteria(Long id){
        return storeService.getByIdWithProductsCriteria(id);
    }

    public StoreDto getByIdWithProductsJPQL(Long id){
        return storeService.getByIdWithProductsJPQL(id);
    }

    public StoreDto getByIdWithProductsGraph(Long id){
        return storeService.getByIdWithProductsGraph(id);
    }
}
