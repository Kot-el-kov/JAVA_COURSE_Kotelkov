package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.model.Store;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StoreRepositoryImpl implements StoreRepository {

    private ArrayList<Store> storeArrayList = new ArrayList<>();
    @Override
    public void createStore(Store store) {
        storeArrayList.add(store);
    }

    @Override
    public Store getStoreById(int id) {
        for (Store store:storeArrayList) {
            if (store.getId()==id){
                return store;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Store> getAllStores() {
        return storeArrayList;
    }

    @Override
    public boolean updateStore(Store store) {
        Store curStore = getStoreById(store.getId());
        if (curStore!=null){
            storeArrayList.remove(curStore);
            storeArrayList.add(store);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStoreById(int id) {
        Store curStore = getStoreById(id);
        if (curStore!=null){
            storeArrayList.remove(curStore);
            return true;
        }
        return false;
    }
}
