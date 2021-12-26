package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.dto.StoreDto;
import com.github.kotelkov.pms.dto.StoreWithProductsDto;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public StoreDto createStore(StoreDto storeDto) {
        return (StoreDto) mapper.convertToDto(storeRepository.
                save((Store) mapper.convertToModel(storeDto,Store.class)),StoreDto.class);
    }

    @Transactional
    @Override
    public StoreDto getStoreById(Long id) {
        return (StoreDto) mapper.convertToDto(storeRepository.getById(id), StoreDto.class);
    }

    @Transactional
    @Override
    public List<StoreDto> getAllStores() {
        return mapper.convertListToDtoList(storeRepository.getAll(),StoreDto.class);
    }

    @Transactional
    @Override
    public StoreDto updateStore(StoreDto storeDto) {
        return (StoreDto) mapper.convertToDto(storeRepository.update((Store)
                mapper.convertToModel(storeDto,Store.class)),StoreDto.class);
    }

    @Transactional
    @Override
    public void deleteStore(Long id) {
        storeRepository.delete(id);
    }

    @Transactional
    @Override
    public StoreWithProductsDto getByIdWithProductsCriteria(Long id) {
        Store store = storeRepository.getByIdWithProductsCriteria(id);
        StoreWithProductsDto storeDto = (StoreWithProductsDto) mapper.convertToDto(store,StoreWithProductsDto.class);
        storeDto.setProductsDto(mapper.convertListToDtoList(store.getProducts(),ProductDto.class));
        return storeDto;
    }

    @Transactional
    @Override
    public StoreDto getByIdWithProductsJPQL(Long id) {
        Store store = storeRepository.getByIdWithProductsJPQL(id);
        StoreDto storeDto = (StoreDto) mapper.convertToDto(store,StoreDto.class);
        return storeDto;
    }

    @Transactional
    @Override
    public StoreDto getByIdWithProductsGraph(Long id) {
        Store store = storeRepository.getByIdWithProductsGraph(id);
        StoreDto storeDto = (StoreDto) mapper.convertToDto(store,StoreDto.class);
        return (StoreDto) mapper.convertToDto(storeRepository.getByIdWithProductsGraph(id),StoreDto.class);
    }
}
