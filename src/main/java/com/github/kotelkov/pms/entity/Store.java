package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@Setter
@Builder
@ToString(exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store-id-sequence")
    @SequenceGenerator(name = "store-id-sequence", sequenceName = "stores_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stores_products",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
