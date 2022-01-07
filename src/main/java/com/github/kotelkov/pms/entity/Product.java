package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "products")
@Getter
@Setter
@Builder
@ToString(exclude = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products-id-sequence")
    @SequenceGenerator(name = "products-id-sequence", sequenceName = "products_id_seq", allocationSize = 1)
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserProfile> wishlists;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "histories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserProfile> histories;
}
