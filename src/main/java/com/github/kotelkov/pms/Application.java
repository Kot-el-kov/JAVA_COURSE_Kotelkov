package com.github.kotelkov.pms;


import com.github.kotelkov.pms.controller.ProductController;
import com.github.kotelkov.pms.controller.StoreController;
import com.github.kotelkov.pms.controller.UserAuthController;
import com.github.kotelkov.pms.controller.UserProfileController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.github.kotelkov.pms");
        ProductController productController = context.getBean(ProductController.class);
        StoreController storeController = context.getBean(StoreController.class);
        UserAuthController userAuthController = context.getBean(UserAuthController.class);
        UserProfileController userProfileController = context.getBean(UserProfileController.class);

        productController.createProduct("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Product1",
                                                  "price" : "100.1",
                                                  "description" : "aaa"
                                                }
                                                """);
        productController.createProduct("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Product2",
                                                  "price" : "1002.1",
                                                  "description" : "bbb"
                                                }
                                                """);

        System.out.println(productController.getProductById("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Product1",
                                                  "price" : "100.1",
                                                  "description" : "aaa"
                                                }
                                                """));
        System.out.println(productController.deleteProductById("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Product2",
                                                  "price" : "1002.1",
                                                  "description" : "bbb"
                                                }
                                                """));
        System.out.println(productController.updateProduct("""
                                                {
                                                  "id" : "1",
                                                  "name" : "ProductUpdated",
                                                  "price" : "100.1",
                                                  "description" : "aaa"
                                                }
                                                """));
        System.out.println(productController.getAllProducts());



        storeController.createStore("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Store2",
                                                  "address" : "Hrodno"
                                                }
                                                """);
        storeController.createStore("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Store1",
                                                  "address" : "Belarus"
                                                }
                                                """);
        System.out.println(storeController.getStoreById("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Store2",
                                                  "address" : "Hrodno"
                                                }
                                                """));
        System.out.println(storeController.deleteStoreById("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Store1",
                                                  "address" : "Belarus"
                                                }
                                                """));
        System.out.println(storeController.updateStore("""
                                                {
                                                  "id" : "2",
                                                  "name" : "StoreUpdate",
                                                  "address" : "Hrodno"
                                                }
                                                """));
        System.out.println(storeController.getAllStores());


        userAuthController.createUserAuth("""
                                                {
                                                  "id" : "2",
                                                  "login" : "user2",
                                                  "password" : "abc123"
                                                }
                                                """);
        userAuthController.createUserAuth("""
                                                {
                                                  "id" : "1",
                                                  "login" : "user1",
                                                  "password" : "dkjfgj223"
                                                }
                                                """);
        System.out.println(userAuthController.getUserAuthById("""
                                                {
                                                  "id" : "2",
                                                  "login" : "user2",
                                                  "password" : "abc123"
                                                }
                                                """));
        System.out.println(userAuthController.deleteUserAuthById("""
                                                {
                                                  "id" : "1",
                                                  "login" : "user1",
                                                  "password" : "dkjfgj223"
                                                }
                                                """));
        System.out.println(userAuthController.updateUserAuth("""
                                                {
                                                  "id" : "2",
                                                  "login" : "userUpdated",
                                                  "password" : "abc123"
                                                }
                                                """));
        System.out.println(userAuthController.getAllUsersAuths());


        userProfileController.createUserProfile("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Oleg",
                                                  "surname" : "Zaharov",
                                                  "email" : "oleg.zaharov@mail.com"
                                                }
                                                """);
        userProfileController.createUserProfile("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Dima",
                                                  "surname" : "Krytoi",
                                                  "email" : "dima.krytoi@mail.com"
                                                }
                                                """);
        System.out.println(userProfileController.getUserProfileById("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Oleg",
                                                  "surname" : "Zaharov",
                                                  "email" : "oleg.zaharov@mail.com"
                                                }
                                                """));
        System.out.println(userProfileController.deleteUserProfileById("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Dima",
                                                  "surname" : "Krytoi",
                                                  "email" : "dima.krytoi@mail.com"
                                                }
                                                """));
        System.out.println(userProfileController.updateUserProfile("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Oleg",
                                                  "surname" : "Updated",
                                                  "email" : "oleg.zaharov@mail.com"
                                                }
                                                """));
        System.out.println(userProfileController.getAllUsersProfiles());

    }
}
