package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.Store;

public interface StoreRepository extends GenericDao<Store, Long>{
    Store getByIdWithProductsCriteria(Long id);
    Store getByIdWithProductsJPQL(Long id);
    Store getByIdWithProductsGraph(Long id);

}
