package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.model.Store;
import com.github.kotelkov.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public void createStore(Store store) {
        storeRepository.createStore(store);
    }

    @Override
    public Store getStoreById(int id) {
        return storeRepository.getStoreById(id);
    }

    @Override
    public ArrayList<Store> getAllStores() {
        return storeRepository.getAllStores();
    }

    @Override
    public boolean updateStore(Store store) {
        return storeRepository.updateStore(store);
    }

    @Override
    public boolean deleteStoreById(int id) {
        return storeRepository.deleteStoreById(id);
    }


}
