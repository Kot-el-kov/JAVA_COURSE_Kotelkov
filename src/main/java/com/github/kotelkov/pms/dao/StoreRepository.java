package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.Store;

public interface StoreRepository extends GenericDao<Store, Long>{
    Store getStoreWithProducts(Long id);
    Store getStoreByName(String name);
}
