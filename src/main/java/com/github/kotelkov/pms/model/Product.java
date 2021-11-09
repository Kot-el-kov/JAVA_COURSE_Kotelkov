package com.github.kotelkov.pms.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private float price;
    private String description;
}
