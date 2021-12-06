package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.StoreDto;
import com.github.kotelkov.pms.dto.StoreWithProductsDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity createStore(@RequestBody StoreDto storeDto) {
        storeService.createStore(storeDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public StoreDto getStoreById(@PathVariable Long id) {
        StoreDto storeDto = Optional.ofNullable(storeService.getStoreById(id)).
                orElseThrow(()-> new ResourceNotFoundException("Store with id: "+id+" not found"));
        return storeDto;
    }

    @GetMapping
    public List<StoreDto> getAllStores() {
        List<StoreDto> storeDtoList = Optional.ofNullable(storeService.getAllStores()).
                orElseThrow(()->new ResourceNotFoundException("Stores not found"));
        return storeDtoList;
    }

    @PutMapping
    public StoreDto updateStore(StoreDto storeDto) {
        return storeService.updateStore(storeDto);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/criteria/{id}")
    public StoreWithProductsDto getByIdWithProductsCriteria(@PathVariable Long id){
        return storeService.getByIdWithProductsCriteria(id);
    }

    public StoreDto getByIdWithProductsJPQL(Long id){
        return storeService.getByIdWithProductsJPQL(id);
    }

    public StoreDto getByIdWithProductsGraph(Long id){
        return storeService.getByIdWithProductsGraph(id);
    }
}
