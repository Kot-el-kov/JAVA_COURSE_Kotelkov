package com.github.kotelkov.pms.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private String description;
}