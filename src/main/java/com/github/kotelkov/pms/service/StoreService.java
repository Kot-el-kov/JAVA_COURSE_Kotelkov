package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.model.Store;

import java.util.List;

public interface StoreService {
    void createStore(Store store);
    Store getStoreById(int id);
    List<Store> getAllStores();
    boolean updateStore(Store store);
    boolean deleteStoreById(int id);
}
