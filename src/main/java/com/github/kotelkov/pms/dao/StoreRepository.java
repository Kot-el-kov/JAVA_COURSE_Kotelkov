package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.model.Store;

import java.util.List;

public interface StoreRepository {
    void createStore(Store store);
    Store getStoreById(int id);
    List<Store> getAllStores();
    boolean updateStore(Store store);
    boolean deleteStoreById(int id);
}
