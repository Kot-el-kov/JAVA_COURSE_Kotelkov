package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.dto.StoreDto;
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
    public void createStore(StoreDto storeDto) {
        storeRepository.save((Store) mapper.convertToModel(storeDto,Store.class));
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
    public void updateStore(StoreDto storeDto) {
        storeRepository.update((Store) mapper.convertToModel(storeDto,Store.class));
    }

    @Transactional
    @Override
    public void deleteStore(Long id) {
        storeRepository.delete(id);
    }

    @Override
    public StoreDto getByIdWithProductsCriteria(Long id) {
        return (StoreDto) mapper.convertToDto(storeRepository.getByIdWithProductsCriteria(id),StoreDto.class);
    }

    @Override
    public StoreDto getByIdWithProductsJPQL(Long id) {
        return (StoreDto) mapper.convertToDto(storeRepository.getByIdWithProductsJPQL(id),StoreDto.class);
    }

    @Override
    public StoreDto getByIdWithProductsGraph(Long id) {
        return (StoreDto) mapper.convertToDto(storeRepository.getByIdWithProductsGraph(id),StoreDto.class);
    }
}
