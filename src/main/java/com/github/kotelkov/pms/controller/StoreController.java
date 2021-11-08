package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.mapper.JsonToModelMapper;
import com.github.kotelkov.pms.model.Store;
import com.github.kotelkov.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreController {

    @Autowired
    JsonToModelMapper jsonToModelMapper;
    @Autowired
    StoreService storeService;
    
    public void createStore(String jsonString) {
        Store store = (Store) jsonToModelMapper.convertToModel(jsonString,Store.class);
        storeService.createStore(store);
    }
    
    public String getStoreById(String jsonString) {
        Store store = (Store) jsonToModelMapper.convertToModel(jsonString,Store.class);
        return jsonToModelMapper.convertToJson(storeService.getStoreById(store.getId()));
    }
   
    public String getAllStores() {
        return jsonToModelMapper.convertToJson(storeService.getAllStores());
    }
   
    public boolean updateStore(String jsonString) {
        Store store = (Store) jsonToModelMapper.convertToModel(jsonString,Store.class);
        return storeService.updateStore(store);
    }
    
    public boolean deleteStoreById(String jsonString) {
        Store store = (Store) jsonToModelMapper.convertToModel(jsonString,Store.class);
        return storeService.deleteStoreById(store.getId());
    }
}
