package com.github.kotelkov.pms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "description")
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stores_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<Store> stores;
}
