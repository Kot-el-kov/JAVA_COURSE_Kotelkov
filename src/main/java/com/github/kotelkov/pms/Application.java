package com.github.kotelkov.pms;


import com.github.kotelkov.pms.controller.ProductController;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.github.kotelkov.pms");
        ProductController productController = context.getBean(ProductController.class);
        productController.createProduct("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Product1",
                                                  "price" : "1001",
                                                  "description" : "aaa"
                                                }
                                                """);
        productController.createProduct("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Product2",
                                                  "price" : "1002",
                                                  "description" : "bbb"
                                                }
                                                """);

        System.out.println(productController.getProductById("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Product1",
                                                  "price" : "1001",
                                                  "description" : "aaa"
                                                }
                                                """));

        productController.updateProduct("""
                                                {
                                                  "id" : "1",
                                                  "name" : "ProductUpdated",
                                                  "price" : "1001",
                                                  "description" : "aba"
                                                }
                                                """);

        productController.deleteProductById("""
                                                {
                                                  "id" : "2",
                                                  "name" : "Product2",
                                                  "price" : "1002",
                                                  "description" : "bbb"
                                                }
                                                """);

        System.out.println(productController.getAllProducts());

        productController.deleteProductById("""
                                                {
                                                  "id" : "1",
                                                  "name" : "Product2",
                                                  "price" : "1002",
                                                  "description" : "bbb"
                                                }
                                                """);

    }
}
