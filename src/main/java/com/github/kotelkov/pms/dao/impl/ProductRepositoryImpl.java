package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    public void createProduct(Product product) {
        productArrayList.add(product);
    }

    @Override
    public Product getProductById(int id) {
        for (Product product:productArrayList) {
            if (product.getId()==id){
                return product;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return productArrayList;
    }

    @Override
    public boolean updateProduct(Product product) {
        Product curProduct = getProductById(product.getId());
        if (curProduct!=null){
            productArrayList.remove(curProduct);
            productArrayList.add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProductById(int id) {
        Product curProduct = getProductById(id);
        if (curProduct!=null){
            productArrayList.remove(curProduct);
            return true;
        }
        return false;
    }
}
