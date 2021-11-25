package com.github.kotelkov.pms;


import com.github.kotelkov.pms.controller.ProductController;
import com.github.kotelkov.pms.controller.StoreController;
import com.github.kotelkov.pms.controller.UserAuthController;
import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.dao.impl.ProductRepositoryImpl;
import com.github.kotelkov.pms.dao.impl.StoreRepositoryImpl;
import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.dto.StoreDto;
import com.github.kotelkov.pms.dto.UserAuthDto;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.service.ProductService;
import com.github.kotelkov.pms.service.impl.ProductServiceImpl;
import com.github.kotelkov.pms.service.impl.StoreServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.github.kotelkov.pms");
        StoreController storeController = context.getBean(StoreController.class);
        ProductController productController = context.getBean(ProductController.class);
        UserAuthController userAuthController = context.getBean(UserAuthController.class);
    }
}
