package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.store.StoreCreateDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.dto.store.StoreWithProductsDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PreAuthorize(value = "hasAnyRole('ADMIN','SELLER')")
    @PostMapping
    public StoreDto createStore(@Valid @RequestBody StoreCreateDto storeCreateDto) {
        return storeService.createStore(storeCreateDto);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/{id}")
    public StoreDto getStoreById(@PathVariable Long id) {
        StoreDto storeDto = Optional.ofNullable(storeService.getStoreById(id)).
                orElseThrow(()-> new ResourceNotFoundException("Store with id: "+id+" not found"));
        return storeDto;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping
    public Page<StoreDto> getAllStores(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                       @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                       @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
        Page<StoreDto> storeDtoList = Optional.ofNullable(storeService.getAllStores(PageRequest.of(page, size, ASC, sort))).
                orElseThrow(()->new ResourceNotFoundException("Stores not found"));
        return storeDtoList;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','SELLER')")
    @PutMapping
    public StoreDto updateStore(@Valid @RequestBody StoreDto storeDto) {
        return storeService.updateStore(storeDto);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','SELLER')")
    @DeleteMapping({"/{id}"})
    public ResponseEntity deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/products/{id}")
    public StoreWithProductsDto getStoreWithProducts(@PathVariable Long id){
        return storeService.getStoreWithProducts(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/name/{name}")
    public StoreDto getStoreByName(@PathVariable String name) {
        return storeService.getStoreByName(name);
    }
}
